supermap :: [a] -> (a -> [b]) -> [b]
supermap [] _ = []
supermap (x:xs) f = (f x) ++ (supermap xs f)
