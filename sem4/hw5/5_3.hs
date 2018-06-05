import Control.Monad

locMax :: (Ord a) => [a] -> Maybe a
locMax (f:s:t:xs) = comp f s t `mplus` locMax (s:t:xs)
locMax _ = Nothing

comp :: (Ord a) => a -> a -> a -> Maybe a
comp x y z | y > x && y > z = Just y
           | otherwise = Nothing