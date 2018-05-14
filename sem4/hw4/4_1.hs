evenMap :: [Int] -> Int
evenMap x = sum $ map (\a -> if (even a) then 1 else 0) x

evenFilter :: [Int] -> Int
evenFilter x = length $ filter even x

evenFoldr :: [Int] -> Int
evenFoldr x = foldr f 0 x where 
                    f a b | even a = b + 1
                          | otherwise = b