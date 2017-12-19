import pickle
import NeuralNetwork as NN
import numpy as np
from PIL import Image
import Converter as c

inputFile = 'Res\\input.png'
outputFile = 'Res\\output.txt'

def loadFromFile(name):
       with open('Res\%s.pickle' % name, 'rb') as f:
              return pickle.load(f)

def letterFromNumber(n):
       if (n == 0): return u'а'
       if (n == 1): return u'б'
       if (n == 2): return u'в'
       if (n == 3): return u'г'
       if (n == 4): return u'д'
       if (n == 5): return u'е'
       if (n == 6): return u'ж'
       if (n == 7): return u'з'
       if (n == 8): return u'и'
       if (n == 9): return u'к'
       if (n == 10): return u'л'
       if (n == 11): return u'м'
       if (n == 12): return u'н'
       if (n == 13): return u'о'
       if (n == 14): return u'п'
       if (n == 15): return u'р'
       if (n == 16): return u'с'
       if (n == 17): return u'т'
       if (n == 18): return u'у'
       if (n == 19): return u'ф'
       if (n == 20): return u'х'
       if (n == 21): return u'ц'
       if (n == 22): return u'ч'
       if (n == 23): return u'ш'
       if (n == 24): return u'щ'
       if (n == 25): return u'ъ'
       if (n == 26): return u'я'
       if (n == 27): return u'э'
       if (n == 28): return u'ю'
       if (n == 29): return u'я'

sizes = loadFromFile('sizes')
weights = loadFromFile('weights')
biases = loadFromFile('biases')

network = NN.Network(sizes)
network.setWeights(weights, biases)

img = Image.open(inputFile)
converter = c.Converter(img, 'Res\converter.txt')
converter.getLetters()

data = np.loadtxt("Res\converter.txt", delimiter=",")
data = data[:,:-1]

f = open(outputFile, 'w')

for d in data:
       letter = letterFromNumber(network.predict(d.reshape(d.size, 1)))
       f.write(letter)

f.close()