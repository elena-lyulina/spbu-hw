reverse' :: [a] -> [a]
reverse' a = helper a [] where
             helper [] revlist = revlist
             helper (x:xs) revlist = helper xs (x:revlist)