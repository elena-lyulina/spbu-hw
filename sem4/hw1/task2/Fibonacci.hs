module Fibonacci where

fibonacci :: Integer -> Integer
fibonacci n = f n 0 1 
f n first sec | n == 0  = first
              | n > 0   = f (n - 1) sec (first + sec)
              | n < 0   = f (n + 1) (sec - first) first