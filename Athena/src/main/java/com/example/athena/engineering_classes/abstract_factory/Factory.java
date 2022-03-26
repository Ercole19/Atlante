package com.example.athena.engineering_classes.abstract_factory;


import com.example.athena.engineering_classes.scene_decorators.HorizontalScrollBarDecorator;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.engineering_classes.scene_decorators.VerticalScrollBarDecorator;

public class Factory {

    public static SearchResultProduct createProduct(ProductTypeEnum productTypeEnum, FormatBundle formatBundle){
        switch (productTypeEnum){
            case HORIZONTAL_ENTRY:
                return createHorizontal(formatBundle);
            case VERTICAL_ENTRY:
                return createVertical(formatBundle);
            default:
                return null ;
        }
    }

    public static SearchResultProduct createHorizontal(FormatBundle formatBundle){
        SearchResultFormatterComponent component = new HorizontalSceneResult() ;
        if(formatBundle.getContainerHeight() < formatBundle.getEntryNumber() * 100.0)
        {
            component = new HorizontalScrollBarDecorator(component) ;
        }
        return new HorizontalProduct(component.buildScene(formatBundle)) ;
    }

    public static SearchResultProduct createVertical(FormatBundle formatBundle)
    {
        SearchResultFormatterComponent component = new VerticalSceneResult() ;
        if(formatBundle.getContainerWidth() < formatBundle.getEntryNumber() * 100.0)
        {
            component = new VerticalScrollBarDecorator(component) ;
        }
        return new VerticalProduct(component.buildScene(formatBundle)) ;
    }
}
