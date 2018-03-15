foundAt :: Int -> [Int] -> Int
foundAt x list = helper x list 0 where
                 helper _ [] _ = error "not found"
                 helper x (y:ys) n | x == y    = n
                                   | otherwise = helper x ys (n + 1)