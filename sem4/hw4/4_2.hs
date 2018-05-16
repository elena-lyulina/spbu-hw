import System.IO

main = do
    hSetBuffering stdin LineBuffering
    doLoop []

doLoop lst = do
    putStrLn "Enter a command: \n 0 - exit \n 1 - add value to sorted list \n 2 - remove value from list \n 3 - print list"
    command <- getLine
    case command of
      '0':_ -> return ()
      '1':_ -> do 
            putStrLn "Enter a value to add"
            value <- getLine
            doLoop $ addValue lst $ read value
      '2':_ -> do 
            putStrLn "Enter a value to remove"
            value <- getLine
            doLoop $ removeValue lst $ read value
      '3':_ -> do 
            print lst
            doLoop lst
      _ -> do 
            putStrLn "Parse error on input coommand, try again"
            doLoop lst
      


addValue :: [Int] -> Int -> [Int]
addValue [] a = [a]
addValue lst@(x:xs) a = if (x >= a) then a:lst else x : (addValue xs a)

removeValue :: [Int] -> Int -> [Int]
removeValue [] a = []
removeValue (x:xs) a = if (x == a) then xs else x : (removeValue xs a)
