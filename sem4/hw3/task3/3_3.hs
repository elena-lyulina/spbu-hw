import Data.List
import GHC.Exts
import Data.Ord

largestSumAt :: (Ord a, Num a) => [a] -> Int
largestSumAt [] = error "not enough elements"
largestSumAt [_] = error "still not enough elements"
largestSumAt l = snd . head $ sortBy (comparing (Down . fst)) $ zip sumList [1..] where 
                       sumList = map (\a -> fst a + snd a) $ zip l $ tail l