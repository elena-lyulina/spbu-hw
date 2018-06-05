import System.IO

main = do
    putStrLn "Enter n to decompose it into terms: "
    n <- getLine
    print (decomp (read n))


helper :: Int -> Int -> [[Int]]
helper n l =  map (\x -> l : x) (filter (all( <= l)) (decomp (n - l)))

helper2 :: Int -> Int -> [[Int]]
helper2 n c | n == c    = helper n n
            | otherwise = (helper n c) ++ (helper2 n (c+1))
            

decomp :: Int -> [[Int]]
decomp 0 = [[]]
decomp n | n == 0    = [[]]
         | n < 0     = error "n must be positive!"
         | otherwise = helper2 n 1
