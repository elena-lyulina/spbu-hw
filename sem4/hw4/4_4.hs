import System.IO
import Data.Char
import Data.List.Split

main = do
    hSetBuffering stdin LineBuffering
    doLoop []

doLoop pb = do
    putStrLn "Enter a command: \n 0 - exit \n 1 - add a contact to phonebook \n 2 - find phone by name \n 3 - find name by phone \n 4 - save phonebook to file \n 5 - import phonebook from file \n 6 - print phonebook \n"
    command <- getLine
    case command of
      '0':_ -> return ()
      '1':_ -> do 
            putStrLn "Enter a name to add:"
            name <- getLine
            putStrLn "Enter a phone to add:"
            phone <- getLine
            doLoop $ (checkName name, checkPhone phone) : pb
      '2':_ -> do 
            putStrLn "Enter a name to find by:"
            name <- getLine
            print $ filter (\(n, p) -> n == name) pb
            doLoop pb
      '3':_ -> do 
            putStrLn "Enter a phone to find by:"
            phone <- getLine
            print $ filter (\(n, p) -> p == phone) pb
            doLoop pb
      '4':_ -> do 
            putStrLn "File name:"
            file <- getLine
            writeFile file (pbToString pb)            
            doLoop pb
      '5':_ -> do 
            putStrLn "File name:"
            file <- getLine
            string <- readFile file
            doLoop $ stringToPb string
      '6':_ -> do 
            print pb
            doLoop pb
      _ -> do 
            putStrLn "Parse error on input coommand, try again"
            doLoop pb

checkName :: String -> String
checkName str | all (\x -> x `elem` (' ':['A'..'z'])) str = str
              | otherwise = error "sorry, only letters and spaces are allowed"
              
checkPhone :: String -> String
checkPhone str | all (isDigit) str = str
               | otherwise = error "sorry, only digits are allowed"
               
pbToString :: [(String, String)] -> String
pbToString lst = concatMap (\(n, p) -> n ++ "," ++ p ++ "\n") lst

stringToPb :: String -> [(String, String)]
stringToPb str = map (splitToPair) $ lines str

splitToPair :: String -> (String, String)
splitToPair str = (head spl, head $ tail spl) where spl = (splitOn "," str)
               
