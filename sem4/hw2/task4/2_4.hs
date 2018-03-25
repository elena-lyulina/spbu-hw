foundAt :: Int -> [Int] -> Int
foundAt x list = snd . head $ filter (\a -> fst a == x) $ zip list [1..]