from PIL import Image
import numpy as np
import zope.interface as i
import IConverter as c

class UpdateConverter():
    @i.implementer(c.InterfaceConverter)
    def __init__(self, image, fileName):
        self.img = image.convert('RGB')
        self.fileName = fileName

    def getLetters(self):
        letters = []
        self.cropImage(letters)
        self.writeToFile(letters)

    def isWhite(self, pixel):
        return np.mean(pixel) > 250

    def endOfBlack(self, lookForWhite, numbOfWhite, w, i, begin, blackWidth):
        # looking for white part; there is at least one line of white pixels; black part is long enough
        return lookForWhite and numbOfWhite >= w and (i - begin) >= blackWidth

    def addSpace(self, lookForSpace, numbOfWhite, whiteWidth, w):
        # looking for space; white part is long enough to be a space
        return lookForSpace and numbOfWhite >= whiteWidth * w

    def cropWhiteLines(self, img, images, blackWidth, whiteWidth):
        imgSpace = Image.new("RGB", (15, 13), (255, 255, 255))
        w, h = img.size
        pixels = [list(img.getdata())[i:i + w] for i in range(0, w * h, w)]
        numbOfWhite = 0
        begin = 0
        lookForWhite = False
        lookForSpace = False
        isSpace = True

        for i in range(h):
            for j in range(w):
                # in case of finding white pixel
                if (self.isWhite(pixels[i][j])):
                    numbOfWhite += 1
                    # checking end of black part, if we aren't looking for white part
                    if (self.endOfBlack(lookForWhite, numbOfWhite, w, i, begin, blackWidth)):
                        images.append(img.crop((0, begin, w, i)))
                        lookForWhite = False
                        lookForSpace = True
                        begin = 0
                        numbOfWhite = 0
                    # checking is this white part a space
                    if (self.addSpace(lookForSpace, numbOfWhite, whiteWidth, w)):
                        images.append(imgSpace)
                        lookForSpace = False
                # in case of finding black pixel
                elif (lookForWhite):
                    numbOfWhite = 0
                elif (not lookForWhite):
                    begin = i
                    lookForWhite = True
                    isSpace = False

        # if cycle has ended on black part or there is only white pixels -> it is space
        if (lookForWhite or isSpace):
            images.append(img.crop((0, begin, w, h)))

    def firstElement(self, tuple):
        return tuple[0]

    def countWhitePixels(self, img):
        # count number of white pixels in each line
        width, height = img.size
        pixels = [list(img.getdata())[j:j + width] for j in range(0, width * height, width)]
        whitePixels = []
        for h in range(height):
            numbOfWhite = 0
            for w in range(width):
                if (self.isWhite(pixels[h][w])):
                    numbOfWhite += 1
            whitePixels.append((numbOfWhite, h))
        # sort ascending
        whitePixels = sorted(whitePixels, key=self.firstElement)
        return whitePixels

    def equalWithError(self, a, b, error):
        return abs(a - b) < error

    def areLettersMerged(self, letters, averageLetterSize):
        letters2 = []

        for i in range(len(letters)):
            width, height = letters[i].size
            n = round(height / averageLetterSize)
            if (n > 1):
                whitePixels = self.countWhitePixels(letters[i])
                divide = 0
                sumH = 0
                # divide into n parts
                while (divide < n - 1 and len(whitePixels) > 0):
                    pixelH = whitePixels.pop()[1]
                    # if white line is near to end of letter
                    if (self.equalWithError(pixelH/averageLetterSize, divide + 1, 0.2)):
                        img = letters[i]
                        letters2.append(img.crop((0, 0, width, pixelH - sumH)))
                        letters[i] = img.crop((0, pixelH - sumH, width, height - sumH))
                        sumH += pixelH
                        divide += 1
            letters2.append(letters[i])

        return letters2

    def cropImage(self, letters):
        rows = []
        tempColumns = []
        columns = []
        # divide into lines
        self.cropWhiteLines(self.img, rows, 8, 6)

        for row in rows:
            # for correct identifying 'ы'-letter
            row = row.rotate(90, expand=True)
            # divide into letters
            self.cropWhiteLines(row, tempColumns, 8, 6)
            # divide merged letters
            tempColumns = self.areLettersMerged(tempColumns, 13)
            tempColumns = list(reversed(tempColumns))
            columns.extend(tempColumns)
            tempColumns = []

        for column in columns:
            column = column.rotate(-90, expand=True)
            self.cropWhiteLines(column, letters, 8, 6)

        # just to check
        i = 0
        for i in range(len(letters)):
            # letters[i] = letters[i].resize((13, 15), Image.ANTIALIAS)
            letters[i].save('Res\LettersSpace\%s.png' %i)
            # count[0] += 1

    def writeToFile(self, letters):
        f = open(self.fileName, 'w')
        width = 13
        height = 15
        max = 255
        for l in letters:
            l = l.resize((width, height), Image.ANTIALIAS)
            for j in (np.mean(l.getdata(), axis=1)):
                j = j / max
                f.write("%.4f" % j + ',')
            f.write('0' + '\n')
