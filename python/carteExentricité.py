# Ce script python sert d'exemple à l'implémentation dans l'application
# Il peut traiter les arguments

"""
Premier argument : Emplacement de l'image à sauvegarder
"""

# importation des packages
import numpy as np
import pandas as pd
import seaborn as sns
import ast as ast
from geopy.distance import geodesic
import scipy.stats as sps
import networkx as nx
import matplotlib.pyplot as plt

import sys
args = sys.argv[1::]

import warnings
warnings.filterwarnings('ignore')

plt.rcParams["figure.figsize"] = (20,15)

# importation du fichier csv comme un panda dataframe
communes=pd.read_csv("../../Graphes/DonneesFourniesGraphes/voisinageCommunesBretonnes.csv", sep=';')

#on enlève les voisins non bretons (22+29+35+56) et les boucles

def amezek(com):
    voisins = [int(num) for num in communes['insee_voisins'][com].split('|')]
    amezek = []
    for vois in voisins :
        if (22000 <= vois and vois<23000) or (29000 <= vois and vois<30000) or (35000 <= vois and vois<36000) or (56000 <= vois and vois<57000) : #on reste en bretagne
            if vois != communes['insee'][com] : #boucle
                if vois not in amezek: # ajout pour éviter les doublons
                    amezek.append(vois)
    return amezek


for x in range(len(communes['insee'])) : 
    amezek(x)

# Création d'un dictionaire représentant la liste des voisins, structure proche des graphes networkx

voisins_dict = {communes['insee'][x] : amezek(x) for x in range(len(communes['insee']))}
voisins_dict[29021]

#creation directe via l'outil networkx
G = nx.from_dict_of_lists(voisins_dict)

#lecture des informations geographique

geo=pd.read_csv("../../Graphes/DonneesFourniesGraphes/communes-geo.csv", sep=';')

geo.head()
geo_lite = geo.iloc[:,[0,1,17,19]]

geo_lite.columns

geo_lite['Geo Point'][0]

# création de la colone Latitude
geo_lite['Latitude']=geo_lite['Geo Point'].apply(lambda x : ast.literal_eval(x)[0])
# création de la colone Longitude
geo_lite['Longitude']=geo_lite['Geo Point'].apply(lambda x : ast.literal_eval(x)[1])

def pos_insee(G,data):
    pos = {} #dictionnaire vide
    for com in G.nodes:
        y = float(data[data['Code Officiel Commune'] == com]['Latitude'].iloc[0])
        x = float(data[data['Code Officiel Commune'] == com]['Longitude'].iloc[0])
        #on peut adapter les coordonnées
        #x=(x- 48.13380133652042)*1000
        #y=(y+2.287539276431153)*1000
    
        pos[com]=[x,y]
    
    return pos


pos_insee = pos_insee(G,geo_lite)

def label_insee(G,data):
    label = {} #dictionnaire vide
    for com in G.nodes:
        lab = data[data['Code Officiel Commune'] == com]['Nom Officiel Commune'].iloc[0]
    
        label[com]=lab
    
    return label

from queue import Queue

def bfs_min_costs(graph, start):
    # Création dict costs avec le sommet de départ à 0 et les autres à infini
    costs = {node: float('inf') for node in graph.nodes}
    costs[start] = 0
    
    q = Queue() # FIFO
    q.put(start)
    
    while not q.empty():
        current_node = q.get()
        for neighbor in graph[current_node].keys():
            new_cost = costs[current_node] + 1

            if new_cost < costs[neighbor]:
                costs[neighbor] = new_cost
                q.put(neighbor)                
    return costs

def calculate_eccentricity(G):
    # Cette fonction calcule l'excentricité de chaque nœud dans le graphe G en utilisant l'algorithme BFS.
    
    eccentricities = dict() # Dictionnaire pour stocker les excentricités calculées
    
    # Boucle sur tous les nœuds du graphe G
    for sommet in G.nodes():
        try:
            # Appliquer l'algorithme BFS pour calculer les distances les plus courtes à partir du nœud sommet
            distances = np.array([float(cost) for cost in bfs_min_costs(G, sommet).values()]) # On transforme les valeurs renvoyées en flotant (pour s'assurer du type traité par numpy)
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
            
    # Retourner le dictionnaire d'excentricités calculées pour chaque nœud        
    return eccentricities

eccentricities = calculate_eccentricity(G)

import geopandas
region = geopandas.read_file("../../Graphes/DonneesFourniesGraphes/communes-geo.geojson")

# Création d'une visualisation à partir de l'eccentricité

plt.close()
ax = region.plot(linewidth=1, edgecolor="grey", facecolor="white")
ax.axis("off")
cmap = plt.get_cmap('plasma')  # Choix de la colormap
nx.draw(G, cmap=cmap, pos=pos_insee, ax=ax,node_size=30, alpha=0.4, edge_color="r", node_color=tuple(eccentricities.values()))

norm = plt.Normalize(min(eccentricities.values()), max(eccentricities.values()))

sm = plt.cm.ScalarMappable(norm=norm, cmap=cmap)
sm.set_array([])
plt.colorbar(sm, ax=ax, orientation="vertical")

plt.savefig(args[0])

plt.show() # Si la carte s'affiche, tout est parfait