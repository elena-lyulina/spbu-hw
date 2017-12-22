import numpy as np
import random

def sigmoid(z):
    """
    sigmoid function
    :param z: argument
    :return: value
    """
    return 1.0 / (1.0 + np.exp(-z))

def sigmoidPrime(z):
    """
    sigmoid derivative function
    :param z: argument
    :return: value
    """
    return sigmoid(z) * (1 - sigmoid(z))

def costFunction(network, test_data, size, onehot=True):
    """
    cost function
    :param network: network you want to test
    :param test_data: test data
    :param onehot: is the output of the network the one-hot vector
    :return: result of cost function
    """
    c = 0
    for example, y in test_data:
        if not onehot:
            y = np.eye(size, 1, k =- int(y))
        yhat = network.feedForward(example)
        c += np.sum((y - yhat)**2)
    return c / len(test_data)


class Network:
    def __init__(self, sizes, output=True, activationFunction=sigmoid, activationPrime=sigmoidPrime):
        """
        initialization of network with random numbers, weights and biases are separated
        :param sizes: list of numbers that define the amount of neurons in each layer
        :param output: print information about testing or not
        """

        self.activationFunction = activationFunction
        self.activationPrime = activationPrime
        self.numLayers = len(sizes)
        self.sizes = sizes
        self.biases = [np.random.randn(y, 1) for y in sizes[1:]]
        self.weights = [np.random.randn(y, x)
                        for x, y in zip(sizes[:-1], sizes[1:])]
        self.output = output

    def setWeights(self, weights, biases):
        """
        set weights and biases
        :param weights: weights
        :param biases: biases
        """
        self.biases = biases
        self.weights = weights

    def feedForward(self, a):
        """
        finds the result of network working
        :param a: input vector
        :return: output vector
        """
        for b, w in zip(self.biases, self.weights):
            a = sigmoid(np.dot(w, a) + b)
        return a

    def predict(self, a):
        """
        predict number of class which has been fired
        :param a: input vector
        :return: number of class
        """
        return np.argmax(self.feedForward(a))

    def SGD(self, trainingData, epochs, miniBatchSize, eta,
            testData=None):
        """
        Training of network using Stochastic Gradient Descent
        :param trainingData: list of tuples (x, y); x - input, y - correct one-hot output
        :param epochs: amount of epochs
        :param miniBatchSize: size of mini-batch
        :param eta: learning rate
        :param testData: list of tuples (x, y); x - input, y - correct one-hot output
        :return: percent of success tests
        """

        if testData is not None: n_test = len(testData)
        n = len(trainingData)
        success_tests = 0
        for j in range(epochs):
            random.shuffle(trainingData)
            mini_batches = [
                trainingData[k:k + miniBatchSize]
                for k in range(0, n, miniBatchSize)]
            for mini_batch in mini_batches:
                self.updateMiniBatch(mini_batch, eta)
            if (testData is not None and self.output):
                success_tests = self.evaluate(testData)
                print("Эпоха {0}: {1} / {2}".format(
                    j, success_tests, n_test))
            elif (self.output):
                print("Эпоха {0} завершена".format(j))
        if (testData is not None):
            return success_tests / n_test

    def updateMiniBatch(self, miniBatch, eta):
        """
        updates weights and biases for 1 mini-batch
        :param miniBatch:  list of tuples (x, y); x - input, y - correct one-hot output
        :param eta: learning rate
        """

        nablaB = [np.zeros(b.shape) for b in self.biases]
        nablaW = [np.zeros(w.shape) for w in self.weights]
        for x, y in miniBatch:
            deltaNablaB, deltaNablaW = self.backProp(x, y)
            nablaB = [nb + dnb for nb, dnb in zip(nablaB, deltaNablaB)]
            nablaW = [nw + dnw for nw, dnw in zip(nablaW, deltaNablaW)]

        eps = eta / len(miniBatch)
        self.weights = [w - eps * nw for w, nw in zip(self.weights, nablaW)]
        self.biases = [b - eps * nb for b, nb in zip(self.biases, nablaB)]

    def backProp(self, x, y):
        """
        back propagation
        :param x: input
        :param y: output
        :return: tuple (nablaB, nablaW) - gradient of objective function
        """

        nablaB = [np.zeros(b.shape) for b in self.biases]
        nablaW = [np.zeros(w.shape) for w in self.weights]

        # feed forward and save
        activation = x
        activations = [x]
        zs = []
        for b, w in zip(self.biases, self.weights):
            z = w.dot(activation) + b
            zs.append(z)
            activation = sigmoid(z)
            activations.append(activation)

        # backward pass
        # output layer:
        delta = (activations[-1] - y) * self.activationPrime(zs[-1])
        nablaB[-1] = delta
        nablaW[-1] = delta.dot(activations[-2].T)

        # for other layers
        for l in range(2, self.numLayers):
            delta = (self.weights[-l + 1].T.dot(delta)) * self.activationPrime(zs[-l])
            nablaB[-l] = delta
            nablaW[-l] = delta.dot(activations[-l - 1].T)
        return nablaB, nablaW


    def evaluate(self, testData):
        """
        finds amount of vectors in testData, that were predicted correctly
        :param testData: test data
        :return: amount of vectors
        """

        testResults = [(self.predict(x), y)
                        for (x, y) in testData]
        return sum(int(x == y) for (x, y) in testResults)


    def cost_derivative(self, outputActivations, y):
        """
        partial differential vector of object function with respect to output activations
        :param output_activations:
        :param y: correct answer
        :return: vector
        """
        return (outputActivations - y)
