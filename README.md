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
	        compile 'com.github.BlackDizel:recyclerview-dotsindicator:1.0.3'
	}
   ```  

Usage

```java
   
   //init
   
   RecyclerView rvItems;
   
   ViewDotsIndicator viewDotsIndicator;
   
   void init(){
	viewDotsIndicator = findViewById(R.id.someId); //just add org.byters.dotsindicator.ViewDotsIndicator to layout
	viewDotsIndicator.init(rvItems);
   }
   
   void update(){ //call only then need to change dots num
	viewDotsIndicator.updateData(3, 0); //where 3 is new dots num, and 0 is selected position
   }
   
```

To custom drawables, use attrs `app:drawableSelected` and `app:drawableBackground`

Result

<img src="https://raw.githubusercontent.com/BlackDizel/recyclerview-dotsindicator/master/dots.png" width="360" height="640"/>
	
