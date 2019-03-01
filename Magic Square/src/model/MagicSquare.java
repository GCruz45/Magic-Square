package model;

public class MagicSquare {

    private boolean startingDirection;
    private int[][] square;

    public MagicSquare() {
    }

    public void generateSquare(int order, String startingSide, String startingDirection) throws IllegalOrderException {

        if (!(order % 2 != 0 && order > 1 && order <= 21)) {//21 parece un limite superior razonable. Se agrega dicho limite para evitar un colapso en el sistema.
            throw new IllegalOrderException("Please enter an odd Order between 3 and 21.");
        }

        square = new int[order][order];

        final String NORTH_WEST = "NW";
        final String NORTH_EAST = "NE";
        final String SOUTH_WEST = "SW";
        final String SOUTH_EAST = "SE";

        switch (startingSide) {//Determina si la direccion (que siempre va hacia afuera) es en sentido (o en contra) de las manecillas del reloj
            case "North":
                this.startingDirection = startingDirection.equals(NORTH_WEST);
                break;

            case "East":
                this.startingDirection = startingDirection.equals(NORTH_EAST);
                break;

            case "South":
                this.startingDirection = startingDirection.equals(SOUTH_EAST);
                break;

            case "West":
                this.startingDirection = startingDirection.equals(SOUTH_WEST);
                break;
        }

        int i;
        int j;

        if (this.startingDirection) {//si (startingDirection == true), entonces es hacia afuera y hacia la izquierda (visto de otro modo, va en contra de las manecilllas del reloj).

            switch (startingSide) {

                case "North":

                    i = (order - 1) / 2;
                    j = 0;

                    square[i][j] = 1;

                    for (int k = 2; k <= order * order; k++) {

                        if ((j - 1 != -1) && (i - 1 != -1)) {//revisa si se sale del cuadrado

                            if (square[i - 1][j - 1] == 0) {//revisa que la siguiente casilla este vacia
                                square[i - 1][j - 1] = k;
                                i--;
                                j--;
                            } else {//Baja una diagonal y la asigna con el numero que sigue
                                if (j + 1 == order) {
                                    j = 0;
                                    square[i][j] = k;
                                } else {
                                    j++;
                                    square[i][j] = k;
                                }
                            }

                        } else {//Se salió del cuadrado

                            int caseInt = 0;

                            if ((i - 1 == -1) && (j - 1 != -1)) {//Es 'i' quien se sale.
                                caseInt = 1;
                            }
                            if ((j - 1 == -1) && (i - 1 != -1)) {//Es 'j' quien se sale.
                                caseInt = 2;
                            }
                            if (i - 1 == -1 && j - 1 == -1) {//Revisa el caso puntual en el que se estuviera trasladando a la esquina opuesta del cuadrado
                                caseInt = 3;
                            }

                            switch (caseInt) {
                                case 1:
                                    if (square[order - 1][j - 1] == 0) {
                                        i = order - 1;
                                        j--;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 2:
                                    if (square[i - 1][order - 1] == 0) {
                                        i--;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 3:
                                    if (square[order - 1][order - 1] == 0) {//Revisa que la nueva esquina este vacia
                                        i = order - 1;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {//De lo contrario, retorna a la esquina original y ocupa la posicion inmediatamente abajo de la de la esquina.
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                            }
                        }
                    }
                    break;

                case "East":

                    i = (order - 1) / 2;
                    j = 0;

                    square[i][j] = 1;

                    for (int k = 2; k <= order * order; k++) {

                        if ((j - 1 != -1) && (i - 1 != -1)) {//revisa si se sale del cuadrado

                            if (square[i - 1][j - 1] == 0) {//revisa que la siguiente casilla este vacia
                                square[i - 1][j - 1] = k;
                                i--;
                                j--;
                            } else {//Baja una diagonal y la asigna con el numero que sigue
                                if (j + 1 == order) {
                                    j = 0;
                                    square[i][j] = k;
                                } else {
                                    j++;
                                    square[i][j] = k;
                                }
                            }

                        } else {//Else: se salió del cuadrado

                            int caseInt = 0;

                            if ((i - 1 == -1) && (j - 1 != -1)) {//Es 'i' quien se sale.
                                caseInt = 1;
                            }
                            if ((j - 1 == -1) && (i - 1 != -1)) {//Es 'j' quien se sale.
                                caseInt = 2;
                            }
                            if (i - 1 == -1 && j - 1 == -1) {//Revisa el caso puntual en el que se estuviera trasladando a la esquina opuesta del cuadrado
                                caseInt = 3;
                            }

                            switch (caseInt) {
                                case 1:
                                    if (square[order - 1][j - 1] == 0) {
                                        i = order - 1;
                                        j--;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 2:
                                    if (square[i - 1][order - 1] == 0) {
                                        i--;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 3:
                                    if (square[order - 1][order - 1] == 0) {//Revisa que la nueva esquina este vacia
                                        i = order - 1;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {//De lo contrario, retorna a la esquina original y ocupa la posicion inmediatamente abajo de la de la esquina.
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                            }
                        }
                    }
                    break;

                case "South":

                    i = (order - 1) / 2;
                    j = 0;

                    square[i][j] = 1;

                    for (int k = 2; k <= order * order; k++) {

                        if ((j - 1 != -1) && (i - 1 != -1)) {//revisa si se sale del cuadrado

                            if (square[i - 1][j - 1] == 0) {//revisa que la siguiente casilla este vacia
                                square[i - 1][j - 1] = k;
                                i--;
                                j--;
                            } else {//Baja una diagonal y la asigna con el numero que sigue
                                if (j + 1 == order) {
                                    j = 0;
                                    square[i][j] = k;
                                } else {
                                    j++;
                                    square[i][j] = k;
                                }
                            }

                        } else {//Else: se salió del cuadrado

                            int caseInt = 0;

                            if ((i - 1 == -1) && (j - 1 != -1)) {//Es 'i' quien se sale.
                                caseInt = 1;
                            }
                            if ((j - 1 == -1) && (i - 1 != -1)) {//Es 'j' quien se sale.
                                caseInt = 2;
                            }
                            if (i - 1 == -1 && j - 1 == -1) {//Revisa el caso puntual en el que se estuviera trasladando a la esquina opuesta del cuadrado
                                caseInt = 3;
                            }

                            switch (caseInt) {
                                case 1:
                                    if (square[order - 1][j - 1] == 0) {
                                        i = order - 1;
                                        j--;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 2:
                                    if (square[i - 1][order - 1] == 0) {
                                        i--;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 3:
                                    if (square[order - 1][order - 1] == 0) {//Revisa que la nueva esquina este vacia
                                        i = order - 1;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {//De lo contrario, retorna a la esquina original y ocupa la posicion inmediatamente abajo de la de la esquina.
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                            }
                        }
                    }
                    break;
                case "West":

                    i = (order - 1) / 2;
                    j = 0;

                    square[i][j] = 1;

                    for (int k = 2; k <= order * order; k++) {

                        if ((j - 1 != -1) && (i - 1 != -1)) {//revisa si se sale del cuadrado

                            if (square[i - 1][j - 1] == 0) {//revisa que la siguiente casilla este vacia
                                square[i - 1][j - 1] = k;
                                i--;
                                j--;
                            } else {//Baja una diagonal y la asigna con el numero que sigue
                                if (j + 1 == order) {
                                    j = 0;
                                    square[i][j] = k;
                                } else {
                                    j++;
                                    square[i][j] = k;
                                }
                            }

                        } else {//Else: se salió del cuadrado

                            int caseInt = 0;

                            if ((i - 1 == -1) && (j - 1 != -1)) {//Es 'i' quien se sale.
                                caseInt = 1;
                            }
                            if ((j - 1 == -1) && (i - 1 != -1)) {//Es 'j' quien se sale.
                                caseInt = 2;
                            }
                            if (i - 1 == -1 && j - 1 == -1) {//Revisa el caso puntual en el que se estuviera trasladando a la esquina opuesta del cuadrado
                                caseInt = 3;
                            }

                            switch (caseInt) {
                                case 1:
                                    if (square[order - 1][j - 1] == 0) {
                                        i = order - 1;
                                        j--;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 2:
                                    if (square[i - 1][order - 1] == 0) {
                                        i--;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 3:
                                    if (square[order - 1][order - 1] == 0) {//Revisa que la nueva esquina este vacia
                                        i = order - 1;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {//De lo contrario, retorna a la esquina original y ocupa la posicion inmediatamente abajo de la de la esquina.
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                            }
                        }
                    }
                    break;
            }
        } else {//La direccion va en sentido de las manecillas del reloj
            switch (startingSide) {
                case "North":

                    i = (order - 1) / 2;
                    j = 0;

                    square[i][j] = 1;

                    for (int k = 2; k <= order * order; k++) {

                        if ((j - 1 != -1) && (i - 1 != -1)) {//revisa si se sale del cuadrado

                            if (square[i - 1][j - 1] == 0) {//revisa que la siguiente casilla este vacia
                                square[i - 1][j - 1] = k;
                                i--;
                                j--;
                            } else {//Baja una diagonal y la asigna con el numero que sigue
                                if (j + 1 == order) {
                                    j = 0;
                                    square[i][j] = k;
                                } else {
                                    j++;
                                    square[i][j] = k;
                                }
                            }

                        } else {//Else: se salió del cuadrado

                            int caseInt = 0;

                            if ((i - 1 == -1) && (j - 1 != -1)) {//Es 'i' quien se sale.
                                caseInt = 1;
                            }
                            if ((j - 1 == -1) && (i - 1 != -1)) {//Es 'j' quien se sale.
                                caseInt = 2;
                            }
                            if (i - 1 == -1 && j - 1 == -1) {//Revisa el caso puntual en el que se estuviera trasladando a la esquina opuesta del cuadrado
                                caseInt = 3;
                            }

                            switch (caseInt) {
                                case 1:
                                    if (square[order - 1][j - 1] == 0) {
                                        i = order - 1;
                                        j--;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 2:
                                    if (square[i - 1][order - 1] == 0) {
                                        i--;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 3:
                                    if (square[order - 1][order - 1] == 0) {//Revisa que la nueva esquina este vacia
                                        i = order - 1;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {//De lo contrario, retorna a la esquina original y ocupa la posicion inmediatamente abajo de la de la esquina.
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                            }
                        }
                    }
                    break;
                case "East":

                    i = (order - 1) / 2;
                    j = 0;

                    square[i][j] = 1;

                    for (int k = 2; k <= order * order; k++) {

                        if ((j - 1 != -1) && (i - 1 != -1)) {//revisa si se sale del cuadrado

                            if (square[i - 1][j - 1] == 0) {//revisa que la siguiente casilla este vacia
                                square[i - 1][j - 1] = k;
                                i--;
                                j--;
                            } else {//Baja una diagonal y la asigna con el numero que sigue
                                if (j + 1 == order) {
                                    j = 0;
                                    square[i][j] = k;
                                } else {
                                    j++;
                                    square[i][j] = k;
                                }
                            }

                        } else {//Else: se salió del cuadrado

                            int caseInt = 0;

                            if ((i - 1 == -1) && (j - 1 != -1)) {//Es 'i' quien se sale.
                                caseInt = 1;
                            }
                            if ((j - 1 == -1) && (i - 1 != -1)) {//Es 'j' quien se sale.
                                caseInt = 2;
                            }
                            if (i - 1 == -1 && j - 1 == -1) {//Revisa el caso puntual en el que se estuviera trasladando a la esquina opuesta del cuadrado
                                caseInt = 3;
                            }

                            switch (caseInt) {
                                case 1:
                                    if (square[order - 1][j - 1] == 0) {
                                        i = order - 1;
                                        j--;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 2:
                                    if (square[i - 1][order - 1] == 0) {
                                        i--;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 3:
                                    if (square[order - 1][order - 1] == 0) {//Revisa que la nueva esquina este vacia
                                        i = order - 1;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {//De lo contrario, retorna a la esquina original y ocupa la posicion inmediatamente abajo de la de la esquina.
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                            }
                        }
                    }
                    break;
                case "South":

                    i = (order - 1) / 2;
                    j = 0;

                    square[i][j] = 1;

                    for (int k = 2; k <= order * order; k++) {

                        if ((j - 1 != -1) && (i - 1 != -1)) {//revisa si se sale del cuadrado

                            if (square[i - 1][j - 1] == 0) {//revisa que la siguiente casilla este vacia
                                square[i - 1][j - 1] = k;
                                i--;
                                j--;
                            } else {//Baja una diagonal y la asigna con el numero que sigue
                                if (j + 1 == order) {
                                    j = 0;
                                    square[i][j] = k;
                                } else {
                                    j++;
                                    square[i][j] = k;
                                }
                            }

                        } else {//Else: se salió del cuadrado

                            int caseInt = 0;

                            if ((i - 1 == -1) && (j - 1 != -1)) {//Es 'i' quien se sale.
                                caseInt = 1;
                            }
                            if ((j - 1 == -1) && (i - 1 != -1)) {//Es 'j' quien se sale.
                                caseInt = 2;
                            }
                            if (i - 1 == -1 && j - 1 == -1) {//Revisa el caso puntual en el que se estuviera trasladando a la esquina opuesta del cuadrado
                                caseInt = 3;
                            }

                            switch (caseInt) {
                                case 1:
                                    if (square[order - 1][j - 1] == 0) {
                                        i = order - 1;
                                        j--;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 2:
                                    if (square[i - 1][order - 1] == 0) {
                                        i--;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 3:
                                    if (square[order - 1][order - 1] == 0) {//Revisa que la nueva esquina este vacia
                                        i = order - 1;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {//De lo contrario, retorna a la esquina original y ocupa la posicion inmediatamente abajo de la de la esquina.
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                            }
                        }
                    }
                    break;
                case "West":

                    i = (order - 1) / 2;
                    j = 0;

                    square[i][j] = 1;

                    for (int k = 2; k <= order * order; k++) {

                        if ((j - 1 != -1) && (i - 1 != -1)) {//revisa si se sale del cuadrado

                            if (square[i - 1][j - 1] == 0) {//revisa que la siguiente casilla este vacia
                                square[i - 1][j - 1] = k;
                                i--;
                                j--;
                            } else {//Baja una diagonal y la asigna con el numero que sigue
                                if (j + 1 == order) {
                                    j = 0;
                                    square[i][j] = k;
                                } else {
                                    j++;
                                    square[i][j] = k;
                                }
                            }

                        } else {//Else: se salió del cuadrado

                            int caseInt = 0;

                            if ((i - 1 == -1) && (j - 1 != -1)) {//Es 'i' quien se sale.
                                caseInt = 1;
                            }
                            if ((j - 1 == -1) && (i - 1 != -1)) {//Es 'j' quien se sale.
                                caseInt = 2;
                            }
                            if (i - 1 == -1 && j - 1 == -1) {//Revisa el caso puntual en el que se estuviera trasladando a la esquina opuesta del cuadrado
                                caseInt = 3;
                            }

                            switch (caseInt) {
                                case 1:
                                    if (square[order - 1][j - 1] == 0) {
                                        i = order - 1;
                                        j--;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 2:
                                    if (square[i - 1][order - 1] == 0) {
                                        i--;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                                case 3:
                                    if (square[order - 1][order - 1] == 0) {//Revisa que la nueva esquina este vacia
                                        i = order - 1;
                                        j = order - 1;
                                        square[i][j] = k;
                                        break;
                                    } else {//De lo contrario, retorna a la esquina original y ocupa la posicion inmediatamente abajo de la de la esquina.
                                        j++;
                                        square[i][j] = k;
                                        break;
                                    }
                            }
                        }
                    }
                    break;
            }
        }
    }

    public int getNumber(int iPos, int jPos) {
        return square[iPos][jPos];
    }
}
