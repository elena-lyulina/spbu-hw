import Data.Tree

data MyTree a = MyEmpty | MyLeaf a | MyNode (MyTree a) a (MyTree a)
instance Foldable MyTree where
   foldr f init MyEmpty = init
   foldr f init (MyLeaf x) = f x init
   foldr f init (MyNode left key right) = foldr f (f key (foldr f init right)) left   
   
not_my_tree = Node "I " [Node "am " [Node "not " [Node "sure " [], Node "it " []]], Node "counts" []]
my_tree = MyNode (MyLeaf "So ") "there " (MyNode (MyLeaf "is ") "another " (MyLeaf "version"))

main = do
      putStr "pre-order traversal: \n"
      print $ foldr (++) [] $ flatten not_my_tree
      print $ foldr (++) [] my_tree
