powOfTwo :: Int -> [Double]
powOfTwo n = helper n [] where 
             helper n list| n == 0   = list
                          | n > 0    = helper (n - 1) (2^^n:list)
                          | n < 0    = helper (n + 1) (2^^n:list)