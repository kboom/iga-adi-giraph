## Find p:

```
(n * (s - 1))/((n - 1) * s) where n = <N>, s = <S>
```

## Find speedup limit

```
lim n->infty (1 / ((1-p) + p / n)) where p = <p>
```

## Example

We pick N=8 as anything more than that will suffer from:
* "Fake threads" as in GCP machines use HT
* Influence from the cluster, as it was run in a single-node mode

For total:
p = 0.968375
s = 31.62

For initialisation:
p = 0.975626
s = 41.0273
 
 
 Everything else single node 8t (12228):
 1-> 989345
 8 -> 
 
 
 Everything else 10x (12288)
 1 -> 294796
 10 -> 63524
