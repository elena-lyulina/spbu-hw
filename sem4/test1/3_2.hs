import System.Random

randomList :: Int -> IO([Int])
randomList 0 = return []
randomList n = do
  r  <- randomRIO (minBound :: Int, maxBound :: Int)
  rs <- randomList (n-1)
  return (r:rs) 
  
changeList :: [a] -> IO([Int])
changeList lst = randomList $ length lst