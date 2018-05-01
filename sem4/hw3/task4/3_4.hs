corrBrackets :: [Char] -> Bool
corrBrackets x = helper [] x True where
                 helper stack [] flag = (stack == [])
                 helper stack str@(s:ss) flag | flag == False                  = False
                                              | isOpndBracket s                = helper (s:stack) ss True
                                              | isClsdBracket s && stack == [] = False
                                              | isClsdBracket s                = helper (tail stack) ss (head stack == oppBracket s) 
                                              | otherwise                      = helper stack ss True

isBracket x = isOpndBracket x || isClsdBracket x
isOpndBracket x = (x == '(') || (x == '[') || (x == '{')
isClsdBracket x = (x == ')') || (x == ']') || (x == '{')
oppBracket ')'  = '('
oppBracket ']'  = '['
oppBracket '}'  = '{' 
