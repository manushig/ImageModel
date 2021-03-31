# Image Model & Cross-Stitch Controller

## Overview
Requirement is to build a model that can be used to manipulate images to produce some interesting effects and to extend the image model by adding to two algorithms for breaking down an image into "chunks", and then use one of these to develop a controller for creating cross-stitch patterns.
- Loading and Saving Images:Standard representation is used where an image can be thought of as simply as a 3D array of integers with rows = height, columns = width and depth = 3. With 8-bit channels, each value is between 0 and 255.
- Filtering Images: A basic operation in many image processing algorithms is filtering. A filter has a "kernel", which is a 2D array of numbers, having odd dimensions (3x3, 5x5, etc.). Given a pixel in the image and a channel, the result of the filter can be computed for that pixel and channel.
- Color transformations: Filtering modifies the value of a pixel depending on the values of its neighbors. Filtering is applied separately to each channel. In contrast, a color transformation modifies the color of a pixel based on its own color. Consider a pixel with color LaTeX: (r,g,b)( r , g , b ). A color transformation results in the new color of this pixel to be LaTeX: (r',g',b')( r ′ , g ′ , b ′ ) such that each of them are dependent only on the values LaTeX: (r,g,b)( r , g , b ).
- Reducing color density: One of the ways to transform the colors in an image is to reduce the number of colors in the image.The technique for preserving the essence of the image breaks down an image that has many colors into an image that is made of dots from just a few colors is known as dithering. A popular technique to dither an image is the Floyd-Steinberg algorithm 
- Image Chunking: A image can be "broken down" into mosaic shapes, by choosing a set of points in the image (called seeds). Another way is chunking the image into regular squares across the rows and columns.This chunking method produces what many think of as an equivalent to pixelating the image.
- Pattern Generation: It generates the chunked image to makes it possible to convert an image that has many pixels into one that has fewer pixels without actually changing the number of colors that the image uses. It generates the cross-stitch pattern from an image.
## List of features.
- Loading and Saving Images
- Filtering Images - Different filters like image blur and image sharpening could be applied.
- Color transformations- Different color transformations like grey scale and sepia tone could be applied.
- Reducing color density - Another way to transform the colors in an image is to reduce the number of colors in the image.
- Chunking Images - Different chunking algorithms can be applied like Mosaic and Pixelate.
- Pattern Generation - Cross-stitch pattern could be generated from an image.

## How To Run. 

Image Model package will have the below mentioned folder structure: 

![](./Screenshots/Code_Structure.JPG)

![](./Screenshots/Code_Structure_2.JPG)

- jar file "CrossStitchController.jar" is saved in the \res\ImageResources folder

- Open the \res\ImageResources folder in command shell

- run the command as below, program does need one argument from the user. That is the file name of batch file which should be in same directory as jar
 
```sh
> java - jar .\CrossStitchController.jar .\BatchFile1.txt
> java - jar .\CrossStitchController.jar .\BatchFile2.txt
```

 ![](./Screenshots/Command_To_Run_Jar.JPG)
                              
 ![](./Screenshots/Command_To_Run_Jar_2.JPG)
 
 ## How to Use the Program.
     
There is no interaction with the user, so user just have to run the jar file as mentioned above with one argument(batch file name)
    
## Description of Example Runs

Results of running the ImageModel.jar file

```sh  
PS C:\Manushi\ProjectsWorkSpace\ImageModel\res\Image Resources> java -jar .\CrossStitchController.jar .\BatchFile1.txt
Started

load command is executed.
blur command is executed.
save command is executed.
load command is executed.
sharpen command is executed.
save command is executed.
load command is executed.
grayscale command is executed.
save command is executed.
load command is executed.
sepia command is executed.
save command is executed.
load command is executed.
dither command is executed.
save command is executed.
load command is executed.
mosaic command is executed.
save command is executed.
load command is executed.
pixelate command is executed.
save command is executed.
load command is executed.
pattern command is executed.
savePattern command is executed.

Done

PS C:\Manushi\ProjectsWorkSpace\ImageModel\res\Image Resources> java -jar .\CrossStitchController.jar .\BatchFile2.txt
Started

load command is executed.
blur command is executed.
save command is executed.
load command is executed.
sharpen command is executed.
save command is executed.
load command is executed.
grayscale command is executed.
save command is executed.
load command is executed.
sepia command is executed.
save command is executed.
load command is executed.
dither command is executed.
save command is executed.
load command is executed.
mosaic command is executed.
save command is executed.
load command is executed.
pixelate command is executed.
save command is executed.
load command is executed.
pattern command is executed.
savePattern command is executed.

Done
```
## These are the ouputs for different requirements:
- Original Image

 ![](./ImageResources/IndiaFlag.png)

- Filtering Images - Blur
  
 ![](./ImageResources/IndiaFlag-blur.png)
  
- Filtering Images - Sharpness
  
  ![](./ImageResources/IndiaFlag-sharpen.png)
 
- Color transformations - Grey Scale
  
  ![](./ImageResources/IndiaFlag-grayscale.png)

- Color transformations - Sepia Tone
  
  ![](./ImageResources/IndiaFlag-sepia.png)

- Dither - 8 values per channel
  
  ![](./ImageResources/IndiaFlag-dither.png)

- Mosaic - 1650 seeds
  
  ![](./ImageResources/IndiaFlag-mosaic-1650.png)

- Pixelate 100 squares across

  ![](./ImageResources/IndiaFlag-pixelate-100.png)

- Cross-Stitch Pattern - This pattern can be seen by opening the file at /ImageResources/IndiaFlag-pattern.txt. This file should be open in UTF-16 BE format.

## Design/Model Changes
### - Version 3.0
##### Model Changes
ImageInterface interface and Image class are added to handle 3D image array. There are changes in ImageModelInterface and ImageModel to give unified the existing interface for controller to use. New methods have been added for the new functionality. 

##### ImageModelInterface interface:
It provides the methods that can load and save images and manipulates images to produce some interesting effects.New methods like pixelate, mosaic, pattern and save pattern have been added. 

##### ImageModel class:
This is the concrete implementation of the ImageModelInterface interface, which provides the implementation of all the required methods (load and save image, do grayscale and sepia, do blue and sharpen, dither, pixelate, mosaic, pattern and savePattern) This class also maintain image object.

##### ImageInterface interface:
It provides the methods that can load and save images and manipulates images to produce some interesting effects.

##### Image class:
This is the concrete implementation of the ImageModelInterface interface, which provides the implementation of all the required methods (load and save image, do color transformations, filter and reduce color density,  pixelate, mosaic, pattern and save pattern) This class also maintain the list of image observers, rgb buffer and pattern data.

##### Clamping class:
Access modifier has been changed to protected.

##### ArrayCopyUtility class:
This class has been removed.

##### DmcFloss class:
This class represents the DMC floss to RGB conversion values representation.

##### ImageOperationsUtility class:
 This class has methods which helps to do various operations on the Image.
 
##### Legend class:
This class represents the Legend values representation which are required at the time of generating the cross-stitch pattern from the image.

##### MosaicInterface interface:
It provides the methods to generate mosaic effect on the image.

##### Mosaic class:
This is the concrete implementation of the MosaicInterface interface, which provides the implementation of all the required methods.

##### PixelateInterface interface:
It provides the methods to generate pixelate effect on the image.

##### Pixelate class:
This is the concrete implementation of the PixelateInterface interface, which provides the implementation of all the required methods.

##### PatternInterface interface:
It provides the methods  to generate cross-stitch pattern from the image.

##### Pattern class:
This is the concrete implementation of the PatternInterface interface, which provides the implementation of all the required methods.

##### ImageCommand interface:
It provides the methods to execute commands on the image.

##### Blur, Dither, GrayScale, LoadImage, Mosaic, Pattern, Pixelate, SaveImage, SavePattern, Sepia and Sharpen classes:
This is the concrete implementation of the ImageCommand interface, which provides the implementation of all the required methods.

##### ImageControllerInterface interface:
This interface takes user inputs, tells model which image manipulations to do on the image.

##### ImageController class:
This is the concrete implementation of the ImageControllerInterface interface, which provides the implementation of all the required methods.

### - Version 2.0
##### ImageModelInterface interface:
It provides the methods that can load and save images and manipulates images to produce some interesting effects.

##### ImageModel class:
This is the concrete implementation of the ImageModelInterface interface, which provides the implementation of all the required methods (load and save image, do color transformations, filter and reduce color density) This class also maintain the list of image observers, rgb buffer data and rgb buffer processed data.

##### FilterInterface interface:
It provides the methods to do filter manipulation like blur and sharpness on the image.

##### Filter class:
This is the concrete implementation of the FilterInterface interface, which provides the implementation of all the required methods.

##### ColorTransformationInterface interface:
It provides the methods to do color transformation manipulation like grey scale and sepia tone on the image.

##### ColorTransformation class:
This is the concrete implementation of the ColorTransformationInterface interface, which provides the implementation of all the required methods.

##### ReducingColorDensityInterface interface:
It provides the methods to reduce color density of the image.To transform the colors in an image, it reduce the number of colors in the image.
 
##### ReducingColorDensity class:
This is the concrete implementation of the ReducingColorDensityInterface interface, which provides the implementation of all the required methods.

##### ArrayCopyUtility class:
It provides the method to create a clone 3D Array.

##### Clamping class:
It provides the methods to "clamp" each value in each channel to avoid overflow and underflow while saving, and to display such images properly.

##### IImageController Interface class: 
It is an interface that is refer by Subject for updating state.

### - Version 1.0:
##### IImageController Interface class:
Provides interface functionality for the user/driver to perform functions save, read, blur, sharp, grey scale, sepia tone, reduce color dennsity 8 colors and reduce color density 16 colors on the image.
   
##### ImageController class:
Intuition for this class was to provide a handler between Image Model and Driver to perform functions save, read, blur, sharp, grey scale, sepia tone, reduce color dennsity 8 colors and reduce color density 16 colors on the image.
   
##### IImageModel Interface:
Provides the feature to initialize, get and set RGB buffer, register, remove and notify the observers
   
##### ImageModel class:
Concrete class which implements the IImageModel, which provides the feature to initialize, get and set RGB buffer, register, remove and notify the observers. It maintains the list of image observers, rgb buffer, height and weight of the image.

##### ImageProcessing Interface
Provides the functionality to read and save the image.
   
##### ImageProcessingImp class:
Concrete class which implements the ImageProcessing, it provides the functionality to read and save the image using image Utilities.

##### ColorTransformation Interface
Provides the functionalty to do color transformation of type Sepia Tone and Gray scale on the image.

##### AbstractColorTransformation class:
Abstract class which implements the ColorTransformation, which provides the feature to do color transformation of type Sepia Tone and Gray scale on the image. It maintains rgb buffer, height and weight of the image.

Concrete classes like SepiaTone and GrayScale extend AbstractColorTransformation, and provide the implemention of color transformated logic.

##### ColorTransformationVisitor interface:
It declares the visit operations for all the types of classes of type ColorTransformation which can be visited.

##### ColorTransformationHandler class
This class implements the ColorTransformationVisitor interface.

##### Filter Interface
Provides the functionalty to do filter of type Blur and sharpness on the image.

##### AbstractFilter class:
Abstract class which implements the Filter, which provides the feature to do filter of type Blur and sharpness on the image. It maintains rgb buffer, height and weight of the image and filter matrix.

Concrete classes like Blur and Sharp extend AbstractFilter, and provide the implemention of filter logic.

##### FilterVisitor interface:
It declares the visit operations for all the types of classes of type Filter which can be visited.

##### FilterHandler class
This class implements the FilterVisitor interface.

##### ColorDensity Interface
Provides the functionalty to do reduce color density of type 8 colors and 16 colors on the image.

##### AbstractColorDensity class:
Abstract class which implements the ColorDensity, which provides the feature to do reduce color density of type 8 colors and 16 colors on the image. It maintains rgb buffer, height and weight of the image and dither matrix.

Concrete classes like Color8 and Color16 extend AbstractColorDensity, and provide the implemention of reduce color density logic.

##### ColorDensityVisitor interface:
It declares the visit operations for all the types of classes of type ColorDensity which can be visited.

##### ColorDensityHandler class
This class implements the ColorDensityVisitor interface.

##### Clamping class:
This provides the functionality to clamp the channel values.

##### ColorTransformationVisitor interface:
It declares the visit operations for all the types of classes of type IGear which can be visited (# of gears allowed for a particular type, to get strength of particular type attack or defense, to get the count of particular gear type)

##### IImageController Interface class: 
It is an interface that is refer by Subject for updating state.

# ASSUMPTIONS
 
1) DMCFloss.txt, batch files(BatchFile1.txt and BatchFile2.txt) and the images (IndiaFlag.png and USFlag.png) should be in same directory as jar file.
2) For pattern generation, it will pixelate images to 100 squares across the width of the image and then generate the pattern.
3) Generated pattern file(IndiaFlag-pattern.txt and USFlag-pattern.txt) should be open in UTF-16 BE encoding format.
4) All output files will be generated in same directory as jar file
5) No of squares across the width of the image should be less than the image width and height.
6) All the DMC codes are prefixed by "DMC-", and sorted in lexicographical order.
  
# Limitations
- None
 
# Citations
- IndiaFlag.png - https://pixabay.com/vectors/flag-india-tricolor-country-nation-159416/
- USFlag.png - https://commons.wikimedia.org/wiki/File:Possible_52-star_U.S._flag.svg
- Bike.png - https://commons.wikimedia.org/wiki/File:KTM_DUKE_200.jpg
- Flower.png - It is the personal image.
- http://my.crazyartzone.com/dmc.asp