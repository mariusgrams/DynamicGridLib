# DynamicGridLib
![](https://raw.githubusercontent.com/mariusgrams/DynamicGridLib/master/images/icon.png)

![](https://img.shields.io/github/issues/mariusgrams/DynamicGridLib) ![](https://img.shields.io/github/forks/mariusgrams/DynamicGridLib) ![](https://img.shields.io/github/stars/mariusgrams/DynamicGridLib) ![](https://img.shields.io/github/license/mariusgrams/DynamicGridLib) 

<img src="https://raw.githubusercontent.com/mariusgrams/DynamicGridLib/master/images/banner.png" alt="" style="max-width:100%;">

Features
-------------
- soon
- soon
- soon

Install
-------------
1. Add the JitPack repository to your build file
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. Add the dependency
```
dependencies {
	        implementation 'com.github.mariusgrams:DynamicGridLib:Tag'
	}
```

Use
-------------
Its a simple example how to use it. For more examples clone project and start app
```
public class SimpleExampleFragment extends Fragment implements IViewCallback {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_simple_example, container, false);

        DynamicGrid dynamicGrid = v.findViewById(R.id.dynamicGrid);
        dynamicGrid.setRowCount(4);
        dynamicGrid.setColumnCount(4);
        dynamicGrid.setViewCallback(this);
        dynamicGrid.setFillWithEmptyItems(true);
        dynamicGrid.addGridItem(new GridItem(0,0,2,1));
        dynamicGrid.addGridItem(new GridItem(2, 0, 1, 3));
        dynamicGrid.addGridItem(new GridItem(0, 1));
        dynamicGrid.addGridItem(new GridItem(1, 2));
        dynamicGrid.addGridItem(new GridItem(0, 3));
        dynamicGrid.build();

        return v;
    }

    @Override
    public View onViewCreate(GridItem gridItem) {
        View view = new View(getContext());
        view.setBackgroundColor(Color.RED);
        return view;
    }
```

Examples
-------------
 - SimpleExample
 - RandomColor
 - Dashboard (soon)
 - Shop (soon)
 - Feed (soon)
 
