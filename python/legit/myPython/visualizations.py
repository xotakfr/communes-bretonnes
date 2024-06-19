# importation des packages nécessaires
import numpy as np
import pandas as pd
import geopandas
import seaborn as sns
import ast as ast
from geopy.distance import geodesic
import scipy.stats as sps
import networkx as nx
import matplotlib.pyplot as plt
from queue import Queue
import sys
import warnings

warnings.filterwarnings('ignore')

args = sys.argv[1::]

plt.rcParams["figure.figsize"] = (20, 15)

# importation du fichier csv comme un panda dataframe
communes = pd.read_csv("./DonneesFourniesGraphes/voisinageCommunesBretonnes.csv", sep=';')

# lecture des informations geographique
geo = pd.read_csv("./DonneesFourniesGraphes/communes-geo.csv", sep=';')


def amezek(com):
    voisins = [int(num) for num in communes['insee_voisins'][com].split('|')]
    amezek = []
    for vois in voisins:
        if (22000 <= vois < 23000) or (29000 <= vois < 30000) or (35000 <= vois < 36000) or (
                56000 <= vois < 57000):  # on reste en bretagne
            if vois != communes['insee'][com]:  # boucle
                if vois not in amezek:  # ajout personnel pour éviter les doublons et/ou valeurs à null
                    amezek.append(vois)
    return amezek


voisins_dict = {communes['insee'][x]: amezek(x) for x in range(len(communes['insee']))}

G = nx.from_dict_of_lists(voisins_dict)
geo_lite = geo.iloc[:, [0, 1, 17, 19]]
# création de la colone Latitude
geo_lite['Latitude'] = geo_lite['Geo Point'].apply(lambda x: ast.literal_eval(x)[0])
# création de la colone Longitude
geo_lite['Longitude'] = geo_lite['Geo Point'].apply(lambda x: ast.literal_eval(x)[1])


def pos_insee(G, data):
    pos = {}  #dictionnaire vide
    for com in G.nodes:
        y = float(data[data['Code Officiel Commune'] == com]['Latitude'].iloc[0])
        x = float(data[data['Code Officiel Commune'] == com]['Longitude'].iloc[0])
        pos[com] = [x, y]

    return pos


pos_insee = pos_insee(G, geo_lite)


def label_insee(G, data):
    label = {}  #dictionnaire vide
    for com in G.nodes:
        lab = data[data['Code Officiel Commune'] == com]['Nom Officiel Commune'].iloc[0]

        label[com] = lab

    return label


label_insee = label_insee(G, geo_lite)

G.remove_edges_from(list(nx.selfloop_edges(G)))

region = geopandas.read_file("./DonneesFourniesGraphes/communes-geo.geojson")

ax = region.plot(linewidth=1, edgecolor="grey", facecolor="lightblue")
ax.axis("off")


def calculate_degrees(G: nx.Graph) -> dict:
    """
    Permet de calculer le degré de l'ensemble des sommets d'un graphe 
    :param G: un graphe NetworkX
    :returns: un dictionnaire associant un sommet à son degré pour l'ensemble des sommets du graphe
    """
    degrees = dict()
    # Création de la liste des voisins pour chaque sommet du graphe
    voisins_dict = {communes['insee'][x]: amezek(x) for x in range(len(communes['insee']))}
    for sommet in G.nodes():
        # Le nombre de degrés d'un graphe est égal au nombre de voisins de celui-ci
        degrees[sommet] = len(voisins_dict[sommet])
    return degrees


def bfs_min_costs(graph: nx.Graph, start: str) -> dict:
    """
    Permet de calculer la longueur du plus court chemin pour chaque sommet du graphe
    :param graph: un graphe NetworkX
    :param start: le sommet de départ
    :returns: la liste des longueurs du plus court chemin pour chaque sommet du graphe
    """
    # Création dict costs avec le sommet de départ à 0 et les autres à infini
    costs = {node: float('inf') for node in graph.nodes}
    costs[start] = 0

    q = Queue()  # FIFO
    q.put(start)

    while not q.empty():
        current_node = q.get()
        for neighbor in graph[current_node].keys():
            new_cost = costs[current_node] + 1

            if new_cost < costs[neighbor]:
                costs[neighbor] = new_cost
                q.put(neighbor)
    return costs


def calculate_eccentricity(G: nx.Graph) -> dict:
    """
    Permet de calculer l'eccentricité de l'ensemble des sommets d'un graphe avec l'algorithme BFS programmé par nous-même
    :param G: un graphe NetworkX
    :returns: un dictionnaire associant un sommet à son eccentricité pour l'ensemble des sommets du graphe
    """
    # Cette fonction calcule l'excentricité de chaque sommet dans le graphe G en utilisant l'algorithme BFS.

    eccentricities = dict()  # Dictionnaire pour stocker les excentricités calculées

    # Boucle sur tous les sommets du graphe G
    for sommet in G.nodes():
        try:
            # Appliquer l'algorithme BFS pour calculer les distances les plus courtes à partir du sommet
            distances = np.array([float(cost) for cost in bfs_min_costs(G,
                                                                        sommet).values()])  # On transforme les valeurs renvoyées en flotant (pour s'assurer du type traité par numpy)
            # On retire les infinis, car il s'agit de points impossible à atteindre
            mask = np.isinf(distances)
            # Remplacement des infs par 0
            distances[mask] = 0
            result_list = distances.tolist()
            # L'excentricité est le maximum des coûts trouvés dans distances
            eccentricities[sommet] = max(result_list)
        except nx.NetworkXNoPath:
            # Si aucun chemin n'est trouvé, l'excentricité est définie comme None
            eccentricities[sommet] = None

    # Retourner le dictionnaire d'excentricités calculées pour chaque sommet        
    return eccentricities


def visualize_degrees():
    # Création d'une visualisation à partir des degrés des sommets du graphe

    degrees = calculate_degrees(G)

    plt.close()
    ax = region.plot(linewidth=1, edgecolor="grey", facecolor="white")
    ax.axis("off")
    cmap = plt.get_cmap('plasma')  # Choix de la colormap
    nx.draw(G, cmap=cmap, pos=pos_insee, ax=ax, node_size=30, alpha=1, edge_color="r",
            node_color=tuple(degrees.values()))

    norm = plt.Normalize(min(degrees.values()), max(degrees.values()))

    sm = plt.cm.ScalarMappable(norm=norm, cmap=cmap)
    sm.set_array([])
    plt.colorbar(sm, ax=ax, orientation="vertical")

    plt.savefig("javafx_visualization")
    plt.show()


def visualize_eccentricities():
    # Création d'une visualisation à partir de l'eccentricité des sommets du graphe

    eccentricities = calculate_eccentricity(G)

    plt.close()
    ax = region.plot(linewidth=1, edgecolor="grey", facecolor="white")
    ax.axis("off")
    cmap = plt.get_cmap('cividis')  # Choix de la colormap
    nx.draw(G, cmap=cmap, pos=pos_insee, ax=ax, node_size=30, alpha=1, edge_color="r",
            node_color=tuple(eccentricities.values()))

    norm = plt.Normalize(min(eccentricities.values()), max(eccentricities.values()))

    sm = plt.cm.ScalarMappable(norm=norm, cmap=cmap)
    sm.set_array([])
    plt.colorbar(sm, ax=ax, orientation="vertical")

    plt.savefig("javafx_visualization")
    plt.show()


def draw_top_Y_arretes(G: nx.Graph, X: str, Y_amount: int) -> None:
    """
    Dessine un graphe représentant les Y arrêtes les plus importantes de la région de Bretagne
    :param G: un graphe NetworkX
    :param X: la commune que l'on souhaite voir avec les Y arrêtes les plus importantes
    :param Y_amount: la quantité d'arrêtes qu'on souhaite sélectionner
    :returns: None
    """
    new_G = nx.Graph()
    # remplissage des listes de couleurs et alpha associés à celles-ci pour les arrêtes
    edge_color_map = []
    edge_alpha_map = []
    # on récupère les Y arrêtes les plus importantes
    important_edges = arretes_importantes(G)[:Y_amount]
    # coloration de l'arrête en fonction de son importance (appartenant à Y ou non)
    for edge in G.edges():
        if edge in important_edges:
            new_G.add_edge(edge[0], edge[1])
            edge_color_map.append("black")
            edge_alpha_map.append(0.75)
        else:
            new_G.add_edge(edge[0], edge[1])
            edge_color_map.append("grey")
            edge_alpha_map.append(0.125)

    # remplissage des listes de couleurs et alpha associés à celles-ci pour les sommets
    node_color_map = []
    node_alpha_map = []
    for node in new_G:
        # si est le sommet X
        if node == X:
            node_color_map.append("yellow")
            node_alpha_map.append(1)
        # sinon
        else:
            node_color_map.append("grey")
            node_alpha_map.append(0.125)

    # on dessine le graphe
    region = geopandas.read_file("./DonneesFourniesGraphes/communes-geo.geojson")
    plt.close()
    ax = region.plot(linewidth=1, edgecolor="grey", facecolor="lightblue")
    ax.axis("off")
    nx.draw_networkx_nodes(new_G, node_color=node_color_map, pos=pos_insee, ax=ax, node_size=100, alpha=node_alpha_map)
    nx.draw_networkx_edges(new_G, edge_color=edge_color_map, pos=pos_insee, ax=ax, width=2.0, alpha=edge_alpha_map)
    plt.savefig("javafx_visualization")
    plt.show()


def arretes_importantes(G: nx.Graph) -> list:
    """
    Renvoie une liste triée par ordre décroissant de la centralité d'intermédiarité de chaque sommet du graphe
    :param G: un graphe NetworkX
    :returns: liste triée par odre décroissant de la centralité d'intermédiarité de chaque sommet du graphe
    """
    betweenness_centralities = nx.edge_betweenness_centrality(G)
    important_edges = sorted(betweenness_centralities, key=betweenness_centralities.get, reverse=True)
    return important_edges


def draw_top_Y_communes(G: nx.Graph, X: str, amount_Y: int) -> None:
    """
    Dessine un graphe représentant les chemins reliant la commune X aux Y communes les plus importantes
    :param G: un graphe NetworkX
    :param X: la commune qu'on souhaite reliée aux Y communes les plus importantes
    :param amount_Y: la quantité de communes les plus importantes qu'on souhaite sélectionner
    :returns: None
    """
    new_G = nx.Graph()
    top_Y_communes = get_top_Y_communes(G, amount_Y)
    # ajout des chemins les plus courts
    for Y in top_Y_communes:
        shortest_path, shortest_distance = chemin_plus_court_de_X_vers_Y(G, X, Y)
        for i in range(len(shortest_path) - 1):
            new_G.add_edge(shortest_path[i], shortest_path[i + 1])

    # remplissage des listes de couleurs et alpha associés à celles-ci en fonction du sommet
    color_map = []
    alpha_map = []
    for node in new_G:
        # si est le sommet X
        if node == X:
            color_map.append('yellow')
            alpha_map.append(1)
        # si est l'un des sommets appartenant à top_Y_communes
        elif node in top_Y_communes:
            color_map.append('red')
            alpha_map.append(1)
        # sinon
        else:
            color_map.append('grey')
            alpha_map.append(0.50)

    # on dessine le graphe 
    region = geopandas.read_file("./DonneesFourniesGraphes/communes-geo.geojson")
    plt.close()
    ax = region.plot(linewidth=1, edgecolor="grey", facecolor="lightblue")
    ax.axis("off")
    nx.draw_networkx_nodes(new_G, node_color=color_map, pos=pos_insee, ax=ax, node_size=100, alpha=alpha_map)
    nx.draw_networkx_edges(new_G, edge_color="black", pos=pos_insee, ax=ax, width=2.0, alpha=1)
    plt.savefig("javafx_visualization")
    plt.show()


def get_top_Y_communes(G: nx.Graph, amount_Y: int) -> list:
    """
    Renvoie une liste triée dans l'ordre décroissant de la centralité de degré de chaque sommet du graphe
    :param G: un graphe NetworkX
    :param amount_Y: la quantité de communes les plus importantes qu'on souhaite sélectionner
    :returns: une liste triée dans l'ordre décroissant de la centralité de degré et contenant les Y-ème premiers valeurs obtenues
    """
    degree_centralities = nx.degree_centrality(G)
    top_Y_communes = sorted(degree_centralities, key=degree_centralities.get, reverse=True)[:amount_Y]
    return top_Y_communes


def chemin_plus_court_de_X_vers_Y(G, commune_X, commune_Y):
    """
    Trouve le plus court chemin de X vers Y dans un graphe
    :param G: un graphe NetworkX
    :param commune_X: la commune de départ
    :param commune_Y: la commune d'arrivée
    :returns: les sommets composant le chemin le plus court entre X et Y ainsi que la distance de celui-ci
    """
    try:
        shortest_path = nx.shortest_path(G, source=commune_X, target=commune_Y)
        shortest_distance = nx.shortest_path_length(G, source=commune_X, target=commune_Y)
        return shortest_path, shortest_distance
    except nx.NetworkXNoPath:
        return None, float('inf')


def draw_Y_highest_priority(G: nx.Graph, amount_Y: int, X: int = -1) -> None:
    """
    Dessine un graphe représentant les Y communes les plus importantes à prioritisées pour l'amélioration du réseau routier
    :param G: un graphe NetworkX
    :param amount_Y: la quantité de communes les plus importantes à prioritisées qu'on souhaite sélectionner
    :param X: valeur par défaut à -1, permettrait à un utilisateur de voir sa commune sur la carte si elle fait partie de top_Y_priority
    :returns: None
    """
    # Chargement du fichier gare.csv
    gares = pd.read_csv("./DonneesFourniesGraphes/gare.csv", sep=';')
    # Création du sous-graphe G_gares avec les communes ayant une gare
    communes_gares = set(gares['codeGare'].unique())
    G_gares = G.subgraph(communes_gares).copy()
    top_Y_priority = get_Y_highest_priority(G, G_gares, amount_Y)
    # création du nouveau graphe
    new_G = nx.Graph()
    for edge in G.edges():
        new_G.add_edge(edge[0], edge[1])

    # création d'une liste pour les ID
    top_Y_priority_ID = [ID for ID, _ in top_Y_priority]
    # création d'un dictionnaire pour le contenu des sommets
    top_Y_priority_content = dict()
    i = 1
    for ID, _ in top_Y_priority:
        top_Y_priority_content[ID] = i
        i = i + 1
    if X != -1:
        if X not in top_Y_priority_content.keys():
            top_Y_priority_content[X] = "?"

    # remplissage des listes de couleurs et alpha associés à celles-ci en fonction du sommet
    color_map = []
    alpha_map = []
    for node in new_G:
        if node == X:
            color_map.append('red')
            alpha_map.append(1)
        elif node in top_Y_priority_ID:
            color_map.append('yellow')
            alpha_map.append(1)
        # sinon
        else:
            color_map.append('grey')
            alpha_map.append(0.25)

    # on dessine le graphe 
    region = geopandas.read_file("./DonneesFourniesGraphes/communes-geo.geojson")
    plt.close()
    ax = region.plot(linewidth=1, edgecolor="grey", facecolor="lightblue")
    ax.axis("off")
    nx.draw_networkx_nodes(new_G, node_color=color_map, pos=pos_insee, ax=ax, node_size=200, alpha=alpha_map)
    nx.draw_networkx_edges(new_G, edge_color="grey", pos=pos_insee, ax=ax, width=2.0, alpha=0.25)
    nx.draw_networkx_labels(new_G, pos=pos_insee, labels=top_Y_priority_content)
    plt.savefig("javafx_visualization")
    plt.show()


def get_Y_highest_priority(G: nx.Graph, sub_G: nx.Graph, amount_Y: int) -> list:
    """
    Renvoie la liste triée dans l'ordre décroissants des Y premières communes les plus importantes à prioritisées
    :param G: un graphe NetworkX
    :param sub_G: un sous-graphe NetworkX de G
    :param amount_Y: la quantité de communes les plus importantes à prioritisées qu'on souhaite sélectionner
    :returns: la liste triée dans l'ordre décroissants des Y premières communes les plus importantes à prioritisées
    """
    # Liste des communes dans sub_G
    communes_avec_gare = list(sub_G.nodes())
    # Liste des communes dans le graphe original G
    communes_total = list(G.nodes())
    # Communes à prioriser (communes dans G mais pas dans sub_G)
    communes_a_prioriser = [commune for commune in communes_total if commune not in communes_avec_gare]
    # Initialisation du dictionnaire pour stocker les centralités de proximité
    proximity_to_gares_prioriser = {}
    # Calcul de la centralité de proximité pour chaque commune à prioriser
    for commune in communes_a_prioriser:
        closeness = nx.closeness_centrality(G, u=commune, distance='weight')
        proximity_to_gares_prioriser[commune] = closeness
    # Trie des communes à prioriser par la centralité de proximité décroissante
    sorted_communes_prioriser = sorted(proximity_to_gares_prioriser.items(), key=lambda x: x[1], reverse=True)
    # Sélectionner les Y communes les plus prioritaires à afficher
    return sorted_communes_prioriser[:amount_Y]


which_visualization = int(args[0]) if args else None
if which_visualization == 1:
    visualize_degrees()
elif which_visualization == 2:
    visualize_eccentricities()
elif which_visualization == 3:
    commune_X = int(args[1])
    top_Y = int(args[2])
    draw_top_Y_arretes(G, commune_X, top_Y)
elif which_visualization == 4:
    commune_X = int(args[1])
    top_Y = int(args[2])
    draw_top_Y_communes(G, commune_X, top_Y)
elif which_visualization == 5:
    if len(args) == 2:
        top_Y = int(args[1])
        draw_Y_highest_priority(G, top_Y)
    elif len(args) == 3:
        top_Y = int(args[1])
        commune_X = int(args[2])
        draw_Y_highest_priority(G, top_Y, commune_X)
else:
    print("mauvais argument/s")
