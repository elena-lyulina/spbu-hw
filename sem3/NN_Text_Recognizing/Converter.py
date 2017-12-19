from PIL import Image
import numpy as np


class Converter:
    """
    разбивает на быквы, обрезая белый полосы
    """
    def __init__(self, image, fileName):
        self.img = image.convert('RGB')
        self.fileName = fileName

    def getLetters(self):
        letters = []
        self.cropImage(letters)
        self.writeToFile(letters)

    def isWhite(self, pixel):
        return np.mean(pixel) > 200

    def cropWhiteLines(self, img, images):
        w, h = img.size
        pixels = [list(img.getdata())[i:i + w] for i in range(0, w * h, w)]
        numbOfWhite = 0
        begin = 0
        lookForWhite = False

        for i in range(h):
            for j in range (w):
                if (self.isWhite(pixels[i][j])):
                    if (lookForWhite):
                        numbOfWhite += 1
                        if (numbOfWhite >= w):
                            images.append(img.crop((0, begin, w, i)))
                            lookForWhite = False
                            begin = 0
                            numbOfWhite = 0
                elif (lookForWhite):
                    numbOfWhite = 0
                elif (not lookForWhite):
                    begin = i
                    lookForWhite = True

        if (begin != 0 or lookForWhite):
            images.append(self.img.crop((0, begin, w, h)))

    def cropImage(self, letters):
        rows = []
        columns = []
        self.cropWhiteLines(self.img, rows)
        for row in rows:
            row = row.rotate(-90, expand=True)
            self.cropWhiteLines(row, columns)
        for column in columns:
            column = column.rotate(90, expand=True)
            self.cropWhiteLines(column, letters)

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


# letterNumber = 30
# f = open('data.txt', 'a')
# width = 13
# height = 15
# max = 255
# for i in range(1, letterNumber + 1):
#     img = Image.open('Letters\%s.png' %i).convert('RGB')
#     letters = []
#     cropImage(img, letters)
#     print(len(letters))

    # получили средний размер 13х15
    # size  = np.array([l.size for l in letters])
    # print(np.mean(size, axis=0))

#     for l in letters:
#         l = l.resize((width, height), Image.ANTIALIAS)
#         for j in (np.mean(l.getdata(), axis=1)):
#             j = j / max
#             f.write("%.4f" % j + ',')
#         f.write(str(i - 1) + '\n')
#
#     # а - записать в конец к файлу
#
#
# f.close

