import System.IO

main = do
        putStrLn (show $ simplify $ derive (Neg (X :*: X)))
        putStrLn (show $ simplify $ derive (Const 6 :*: (X :^: 3)))
        putStrLn (show $ simplify $ derive (Const 2 :*: X :*: Const 1 :*: X :*: X))
        putStrLn (show $ simplify $ derive (Const 0 :*: X :*: Const 1 :*: X :*: X))
        putStrLn (show $ simplify $ derive ((X :-: Const 0) :/: (X :+: Const 3)))
        putStrLn (show $ simplify $ derive (X :/: Const 0))


data Expr = X | Const Int | Expr :+: Expr | Expr :-: Expr | Expr :/: Expr | Expr :*: Expr | Expr:^: Int | Neg Expr

instance Show Expr where
    show X = "x"
    show (Const c) = show c
    show (op1 :+: op2) = "(" ++ show op1 ++ " + " ++ show op2 ++ ")"
    show (op1 :-: op2) = "(" ++ show op1 ++ " - " ++ show op2 ++ ")"
    show (op1 :/: op2) = "(" ++ show op1 ++ " / " ++ show op2 ++ ")"
    show (op1 :*: op2) = "(" ++ show op1 ++ " * " ++ show op2 ++ ")"
    show (op :^: pow) = show op ++ "^" ++ show pow
    show (Neg op) = "-" ++ show op
    
derive :: Expr -> Expr
derive e = case e of
            Const c -> Const 0
            X -> Const 1
            op1 :+: op2 -> derive op1 :+: derive op2
            op1 :-: op2 -> derive op1 :-: derive op2
            op1 :*: op2 -> (derive op1 :*: op2) :+: (derive op2 :*: op1)
            op1 :/: op2 -> ((derive op1 :*: op2) :-: (derive op2 :*: op1)) :/: (op2 :*: op2)
            op :^: pow -> Const pow :*: (op  :^: (pow -1)) :*: (derive op)
            Neg op -> Neg (derive op)
            
simplify' :: Expr -> Expr
simplify' operation = case operation of
            op :+: Const 0 -> simplify' op
            op :-: Const 0 -> simplify' op   
            op :*: Const 1 -> simplify' op
            op :*: Const 0 -> Const 0
            op :/: Const 1 -> simplify' op
            op :/: Const 0 -> error "Division by zero is not allowed!"
            op :^: 0 -> Const 1
            op :^: 1 -> simplify' op
            Neg (Neg op) -> simplify' op
            
            Const 0 :+: op -> simplify' op
            Const 0 :-: op -> Neg (simplify' op)
            Const 1 :*: op -> simplify' op
            Const 0 :*: op -> Const 0
            Const 0 :/: op -> Const 0
            
            Const a :+: Const b -> Const (a + b)
            Const a :-: Const b -> Const (a - b)
            Const a :*: Const b -> Const (a * b)
            Const a :/: Const b -> Const (a `div` b)
            Const a :^: pow -> Const (a ^ pow)            
            _ -> operation
            
simplify :: Expr -> Expr
simplify expr = case expr of 
            op1 :+: op2 -> simplify' (simplify op1 :+: simplify op2)
            op1 :*: op2 -> simplify' (simplify op1 :*: simplify op2)
            op1 :-: op2 -> simplify' (simplify op1 :-: simplify op2)
            op1 :/: op2 -> simplify' (simplify op1 :/: simplify op2)
            op :^: pow ->  simplify' (simplify op :^: pow)
            Neg op ->  simplify' (Neg $ simplify op)
            _ -> expr


