> Toute les classes Objet cité ici doivent implémenter [Comparable (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html)

# Comment utiliser une classe implémentant Itri

Pour utiliser une classe implémentant Itri, commencez par créer un objet :
Exemple : 
new TriParSelection(ArrayList<Objet>)

Et appelez la méthode trier() liée à cet objet, l'ArrayList sera alors trié selon la grandeur renvoyée par la méthode compareTo() de ses éléments

# Comment utiliser une classe implémentant ISearch

>  BinarySearcher suppose que l'Arraylist est trié
Commencez par créer un objet implémentant ISearch/
Par exemple :
new BinarySearcher();

.exists(ArrayList<Objet>, Objet à rechercher) -> Renvoie un booléen si l'objet existe dans l'Arraylist
.search(ArrayList<Objet>, Objet à rechercher) -> Renvoie l'ID de la case de l'ArrayList qui contient l'élément