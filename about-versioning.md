Pour ce qui est du nommage des versions j'ai pensé à ce système:

**< releases > . < functionalities-tested > . < functionalities-not-tested >**

explication:

* < functionalities-not-tested > : c'est le nombre de fonctionnalités **majeurs** actuellment implémentées mais non testées
* < functionalities-tested > : c'est le nombre de fonctionnalités **majeurs** implémentées et testées
* < releases > : incrémenté uniquement quand toutes les fonctionnalités **majeurs** sont implémentées et testées
  
Pour les nouvelles version, une fois atteint la v-**1.0.0** :

+ < functionalities-not-tested > : c'est le nombre de fonctionnalités **mineurs** actuellment implémentées mais non testées
+ < functionalities-tested > : c'est le nombre de fonctionnalités **mineurs** implémentées et testées
+ < releases > : incrémenté uniquement quand toutes les fonctionnalités **mineurs** sont implémentées et testées
