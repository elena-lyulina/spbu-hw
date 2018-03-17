sum3 :: (Num a) => [a] -> [a] -> [a] -> [a]
sum2 a [] = a
sum2 [] b = b
sum2 (a:as) (b:bs) = (a + b) : (sum2 as bs)
sum3 a b c = (sum2 a (sum2 b c)