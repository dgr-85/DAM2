class Punt:
    """[Summary]

    Aquesta classe parteix del concepte matemàtic de punt com a ubicació en un espai de dues dimensions.
    Inclou una sèrie de mètodes que permeten definir les coordenades d'un punt qualsevol i calculen algunes
    relacions que es poden establir amb l'origen de coordenades, és a dir, el punt (0, 0).

    El mètode __init__ defineix el constructor bàsic d'un punt definit per l'usuari. Els paràmetres són:

        x: Número enter que representa la coordenada horitzontal. Per defecte és 0, si no s'introdueix cap valor.

        y: Número enter que representa la coordenada vertical. Per defecte és 0, si no s'introdueix cap valor.
    """

    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y

    def distancia_origen(self):
        """[Summary]

        Mètode que calcula la distància del punt a l'origen de coordenades (0, 0).

        :return: Unitats totals que separen els dos punts.
        :rtype: Número enter positiu.
        """
        return ((self.x ** 2) + (self.y ** 2)) ** 0.5

    def __str__(self):
        """[Summary]

        Mètode que expressa les coordenades del punt en format de text.

        :return: Les coordenades del punt en format f-string.
        :rtype: String en forma de tupla.
        """
        return f'({self.x}, {self.y})'  # format de cadena amb f-string

    def punt_intermedi(self, altre):
        """[Summary]

        Mètode que rep les coordenades d'un punt i les compara amb les del punt definit per l'usuari per a
        obtenir les coordenades del punt situat al centre de la distància que els separa.

        :param altre: Punt a comparar amb el de l'usuari.
        :type altre: Tupla
        :return: Punt situat al mig de la distància que separa el punt de l'usuari i el punt altre.
        :rtype: Tupla
        """
        mx = (self.x + altre.x) / 2
        my = (self.y + altre.y) / 2
        return Punt(mx, my)

    def pendent_al_origen(self):
        """[Summary]

        Mètode que traça una recta unint l'origen de coordenades (0,0) i el punt de l'usuari i en calcula el pendent.
        El pendent és la tangent de l'angle que la recta forma amb l'eix horitzontal positiu, de manera que pot ser
        positiu o negatiu segons la inclinació de la recta.

        :raises ZeroDivisionError: Si x és 0 la recta és vertical, no es "desplaça" al llarg de l'eix horitzonal i
            significa que el seu pendent és infinit, cosa que es reflecteix en el seu càlcul perquè implica dividir
            entre 0.
        :return: El valor del pendent de la recta.
        :rtype: Número enter.
        """
        try:
            m = self.y / self.x
        except ZeroDivisionError:
            raise Exception('pendent infinit')
        return m

    def coeficients_recta(self, altre):
        """[Summary]

        Mètode que rep les coordenades d'un punt, en traça una recta que l'uneix amb el punt de l'usuari, i en calcula
        el coeficients m i n necessaris per a trobar l'equació y = mx + n que defineix la recta.

        :param altre: Punt a ajuntar amb el de l'usuari per a traçar la recta.
        :type altre: Tupla
        :raises ZeroDivisionError: El paràmetre m és el pendent de la recta, de manera que si la recta és vertical,
            no es "desplaça" al llarg de l'eix horitzonal i significa que el seu pendent és infinit, cosa que es
            reflecteix en el seu càlcul perquè implica dividir entre 0.
        :return: Els paràmetres m i n de l'equació de la recta.
        :rtype: Tupla.
        """
        try:
            m = (altre.y - self.y) / (altre.x - self.x)
        except ZeroDivisionError:
            raise Exception('pendent infinit')
        n = self.y - m * self.x
        return (m, n)
