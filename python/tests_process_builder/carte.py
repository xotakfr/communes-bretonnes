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
communes=pd.read_csv("./DonneesFourniesGraphes/voisinageCommunesBretonnes.csv", sep=';')

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

geo=pd.read_csv("./DonneesFourniesGraphes/communes-geo.csv", sep=';')

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

label_insee = label_insee(G,geo_lite)

nx.draw(G,pos=pos_insee,node_size=10, alpha=0.4, edge_color="r", font_size=10, labels=label_insee)
plt.savefig(args[0])

plt.show() # Si la carte s'affiche, tout est parfait