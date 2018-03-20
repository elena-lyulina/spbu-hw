str :: Char -> Int -> [Char]
str c 0 = []
str c n = c : (str c (n - 1))

rhombus num = helper num 1 where
            helper n' nstr | nstr < 1      = []
			               | nstr < n'     = (str ' ' (n' - nstr)) ++ (str 'x' (2 * n' - 1 - 2 * nstr)) ++ ['\n'] ++ ((str ' ' (n' - nstr))) ++ (helper n' (nstr + 1))
			               | n' == nstr    = str 'x' (2*n' - 1) ++ ['\n'] ++ (helper n' (nstr + 1))
						   | nstr > (2 * n' - 1) = []
						   | nstr > n'     = (str ' ' (nstr - n')) ++ (str 'x' (2 * n' - 1 - 2 * (nstr - n'))) ++ ['\n'] ++ ((str ' ' (nstr - n'))) ++ (helper n' (nstr + 1))
printRhombus n = do putStr (rhombus n)
						  
                          			
	