import zope.interface as i
import numpy as np

class InterfaceConverter(i.Interface):
    """
    Interface for converter image into letters, written as pixels to file
    """
    img = i.Attribute("""Input image""")
    fileName = i.Attribute("""File path for image's pixels data""")

    def getLetters(self):
        """
        getting letters from image and writing to the file
        """

    def isWhite(self, pixel):
        """
        checking is this pixel white
        :param pixel: pixel (R, G, B)
        :return: true if it's white, false if not
        """

    def cropWhiteLines(self, img, images):
        """
        dividing into lines, cropping white parts
        :param img: image to crop
        :param images: list to put cropped images
        """

    def cropImage(self, letters):
        """
        dividing image into lines, than into letters, than cropping white lines up and down
        :param letters: list to put cropped images
        """

    def writeToFile(self, letters):
        """
        writing pixels of letters into the file
        :param letters: list of images
        """