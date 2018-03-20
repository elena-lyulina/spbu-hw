matrix :: Int -> [[Int]]
matrix n = map (\x -> helper n x x) [1..n]

helper n a 1 = [a..n]
helper n a count = a : helper n a (count - 1)