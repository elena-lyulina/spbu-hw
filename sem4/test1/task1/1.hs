ones :: [Integer]
ones = 1 : (-1) : ones
numbers = 1: (map (*(-1))(zipWith (+) ones numbers))