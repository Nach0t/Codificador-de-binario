/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller_2;




import java.awt.*;


public class Colors {
    
    int x = 0;
int y = 0;

int pixel = image.getRGB(x, y);

Color color = new Color(pixel, true);

int red = color.getRed();
int green = color.getGreen();
int blue = color.getBlue();

String binaryRed = Integer.toBinaryString(red);
String binaryGreen = Integer.toBinaryString(green);
String binaryBlue = Integer.toBinaryString(blue);

// La variable "binaryRed" almacena la representación binaria del valor de rojo.
// La variable "binaryGreen" almacena la representación binaria del valor de verde.
// La variable "binaryBlue" almacena la representación binaria del valor de azul.


}
