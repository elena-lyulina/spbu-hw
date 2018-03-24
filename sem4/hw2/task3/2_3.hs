sumOfNumbers :: Integer -> Integer
sumOfNumbers x = helper x 0 where
                 helper x sum | abs x < 10 = abs x + sum
                              | otherwise  = helper (quot(abs x) 10) (sum + rem (abs x) 10)