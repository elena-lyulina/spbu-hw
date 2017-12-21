import zope.interface as i
import IConverter as c
import SimpleConverter as sc
import UpdateConverter as uc

class AbstractRecognizingFactory(i.Interface):
    """
    abstract factory for size, weight and biases file path and for converter
    """
    sizeFilePath = i.Attribute("""File path for size""")
    weightsFilePath = i.Attribute("""File path for weights""")
    biasesFilePath = i.Attribute("""File path for biases""")

    def createConverter(self, img, fileName):
        """
        create converter
        :return: converter
        """
        return c.InterfaceConverter()
    def letterFromNumber(self, number):
        """
        identify the letter by number
        :return: the letter
        """

class SimpleRecognizer(object):
    """
    Doesn't recognize some difficult letters like 'ё', 'ы', 'й' and spaces
    Needs no information about font size, but input size
    for neural network is 13x15 (average size for letters was found empirically)
    """
    @i.implementer(AbstractRecognizingFactory)

    def __init__(self):
        self.sizeFilePath = 'simpleSize'
        self.weightsFilePath = 'simpleWeights'
        self.biasesFilePath = 'simpleBiases'

    def createConverter(self, img, filePath):
        return sc.SimpleConverter(img, filePath)

    def letterFromNumber(self, n):
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


class UpdateRecognizer(object):
    """
    Does recognize some difficult letters like 'ё', 'ы', 'й' and spaces
    Uses information about font size, so be sure that the letters size is
    about 13x15 (like an average letters size, found empirically), the font size is 16
    """
    @i.implementer(AbstractRecognizingFactory)

    def __init__(self):
        self.sizeFilePath = 'updateSize'
        self.weightsFilePath = 'updateWeights'
        self.biasesFilePath = 'updateBiases'

    def createConverter(self, img, filePath):
        return uc.UpdateConverter(img, filePath)

    def letterFromNumber(self, n):
        if (n == 0): return u' '
        if (n == 1): return u'а'
        if (n == 2): return u'б'
        if (n == 3): return u'в'
        if (n == 4): return u'г'
        if (n == 5): return u'д'
        if (n == 6): return u'е'
        if (n == 7): return u'ё'
        if (n == 8): return u'ж'
        if (n == 9): return u'з'
        if (n == 10): return u'и'
        if (n == 11): return u'й'
        if (n == 12): return u'к'
        if (n == 13): return u'л'
        if (n == 14): return u'м'
        if (n == 15): return u'н'
        if (n == 16): return u'о'
        if (n == 17): return u'п'
        if (n == 18): return u'р'
        if (n == 19): return u'с'
        if (n == 20): return u'т'
        if (n == 21): return u'у'
        if (n == 22): return u'ф'
        if (n == 23): return u'х'
        if (n == 24): return u'ц'
        if (n == 25): return u'ч'
        if (n == 26): return u'ш'
        if (n == 27): return u'щ'
        if (n == 28): return u'ъ'
        if (n == 29): return u'ы'
        if (n == 30): return u'ь'
        if (n == 31): return u'э'
        if (n == 32): return u'ю'
        if (n == 33): return u'я'



