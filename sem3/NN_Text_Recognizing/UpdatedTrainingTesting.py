import NeuralNetwork
import numpy as np
import random
import pickle
from PIL import Image
import UpdatedConverter as uc

"""
These parameters were found for data with different fonts, percent of success predictions is 94,1
"""

inputSize = 15 * 13  # 195 neurons in the input layer - average width*height for letters
hiddenLayer1 = 130 # 130 neurons in the first hidden layer
hiddenLayer2 = 60  # 60 neurons in the second hidden layer
outputSize = 34  # 34 neurons in the output layer - for one to each letter and one for space

epochs = 150
miniBatchSize = 20
eta = 4 # 130 40 15 3 - 10 batch 0.9158

dataFile = "updatedData.txt"
sizeFile = "updatedSize"
weightsFile = "updatedWeights"
biasesFile = "updatedBiases"

def writeToFile(name, data):
    with open('Res\%s.pickle' % name, 'wb') as f:
        pickle.dump(data, f)

data = np.loadtxt("Res\%s" % dataFile, delimiter=",")

# part(25%) of data goes to test
np.random.seed(42)
test_index = np.random.choice([True, False], len(data), replace=True, p=[0.25, 0.75])
test = data[test_index]
train = data[np.logical_not(test_index)]

train = [(d[:inputSize][:, np.newaxis], np.eye(outputSize, 1, k=-int(d[-1]))) for d in train]
test = [(d[:inputSize][:, np.newaxis], d[-1]) for d in test]

# print(test[0])

sizes = [inputSize, hiddenLayer1, hiddenLayer2, outputSize]
random.seed(1)
np.random.seed(1)
network = NeuralNetwork.Network(sizes)
print(network.SGD(train, epochs, miniBatchSize, eta, test))

writeToFile(weightsFile, network.weights)
writeToFile(biasesFile, network.biases)
writeToFile(sizeFile, sizes)

