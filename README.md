# recyclerview-dotsindicator
simple dots indicator for recycler view items

[![](https://jitpack.io/v/BlackDizel/recyclerview-dotsindicator.svg)](https://jitpack.io/#BlackDizel/recyclerview-dotsindicator)

[jitpack](https://github.com/jitpack)

[library on jitpack website](https://jitpack.io/#BlackDizel/recyclerview-dotsindicator)

To install the library add: 
 
   ```gradle
   repositories { 
        jcenter()
        maven { url "https://jitpack.io" }
   }
   	dependencies {
	        compile 'com.github.BlackDizel:recyclerview-dotsindicator:1.0.1'
	}
   ```  

Usage

```java
   
   //init
   
   RecyclerView rvItems;
   LayoutManger manager;
   FrameLayout flDots; //container for dots
   
   DotsIndicatorHelper dotsIndicatorHelper;
   
   void init(){
            dotsIndicatorHelper = new DotsIndicatorHelper(flDots, rvItems, layoutManager);
   }
   
   void update(){ //call only then need to change dots num
            dotsIndicatorHelper.updateData(3); //where 3 is new dots num
   }
   
```
	
	
