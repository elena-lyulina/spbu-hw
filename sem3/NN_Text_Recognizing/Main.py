import pickle
import NeuralNetwork as nn
import numpy as np
from PIL import Image
import IRecognizingFactory as rg

inputFile = 'Res\\input.png'
outputFile = 'Res\\output.txt'
tempFile = 'Res\\converter.txt'

def loadFromFile(name):
       with open('Res\%s.pickle' % name, 'rb') as f:
              return pickle.load(f)

factory = rg.SimpleRecognizer()

sizes = loadFromFile(factory.sizeFilePath)
weights = loadFromFile(factory.weightsFilePath)
biases = loadFromFile(factory.biasesFilePath)

network = nn.Network(sizes)
network.setWeights(weights, biases)

img = Image.open(inputFile)
converter = factory.createConverter(img, tempFile)
converter.getLetters()

data = np.loadtxt(tempFile, delimiter=",")
data = data[:,:-1]

f = open(outputFile, 'w')

for d in data:
       letter = factory.letterFromNumber(network.predict(d.reshape(d.size, 1)))
       f.write(letter)

f.close()