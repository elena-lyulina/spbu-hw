sum' :: [Int] -> [Int] -> [Int] -> [Integer]
sum' a b c = helper a b c [] where
             helper a b c res | allNull a b c = reverse res
                              | otherwise     = helper (tailOrNull a) (tailOrNull b) (tailOrNull c) ((headOr0 a + headOr0 b + headOr0 c):res)

             allNull a b c = (a == [] && b == [] && c == [])
             tailOrNull a = if (a == []) then [] else (tail a)
             --should i worry about arithmetic overflows?
             headOr0 a = if (a == []) then 0 else (toInteger (head a))