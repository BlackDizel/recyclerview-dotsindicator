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
    void initDots(RecyclerView rv,
                  FrameLayout layout, //container for dots
                  LinearLayoutManager layoutManager,
                  int itemsNum) {

        rv.addOnScrollListener(new ScrollListener(layoutManager);

        dotsIndicator = new DotsIndicator(layout);
        dotsIndicator.init(); //simple add linearLayout to layout

        dotsIndicator.setDots(itemsNum); //generate dots in linearlayout
        dotsIndicator.initDots(); //reset selected dot
    }

    private class ScrollListener extends RecyclerView.OnScrollListener {

        private LinearLayoutManager layoutManager;

        ScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int pos = layoutManager.findFirstVisibleItemPosition();

            dotsIndicator.onScroll(pos, layoutManager.findViewByPosition(pos));
        }
    }
```
	
	
