and' :: [Bool] -> Bool
and' [] = True
and' (x:xs) = x && and' xs

all' :: (a -> Bool) -> [a] -> Bool
all' f = and' . map f