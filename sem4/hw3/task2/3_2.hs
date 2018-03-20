only179 :: [Integer]
lst = [1, 7, 9]
only179 = lst ++ (concatMap (\x -> map (+ (x * 10)) lst ) only179)