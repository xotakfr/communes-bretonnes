# Comment utiliser une classe implémentant Itri
Pour utiliser une classe implémentant Itri, commencez par créer un objet :
Exemple : 
new TriParSelection(ArrayList<Objet>)

Et appelez juste la fonction .trier() lié à cet objet, l'arrayList sera alors trié selon la grandeur compareTo

# Comment utiliser le binarySearcher
BinarySearcher suppose que l'arraylist est trié

exists(ArrayList<Objet>, Objet à rechercher) -> Renvoie un booléen si l'objet existe dans l'arraylist
search(ArrayList<Objet>, Objet à rechercher) -> Renvoie l'ID de la case de l'arrayList qui contient l'élément