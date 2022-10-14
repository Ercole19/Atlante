package com.example.athena.engineering_classes.search_result_factory;


import com.example.athena.engineering_classes.scene_decorators.HorizontalScrollBarDecorator;
import com.example.athena.engineering_classes.scene_decorators.LegendDecorator;
import com.example.athena.engineering_classes.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.engineering_classes.scene_decorators.VerticalScrollBarDecorator;

public class SearchResultFactory {

    private SearchResultFactory() {}

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
        if(formatBundle.getContainerWidth() < formatBundle.getEntryNumber() * formatBundle.getEntrySize())
        {
            component = new HorizontalScrollBarDecorator(component) ;
        }
        return new HorizontalProduct(component.buildScene(formatBundle)) ;
    }

    public static SearchResultProduct createVertical(FormatBundle formatBundle)
    {
        SearchResultFormatterComponent component = new VerticalSceneResult() ;
        if(formatBundle.getContainerHeight() - 40 < formatBundle.getEntryNumber() * formatBundle.getEntrySize())
        {
            component = new VerticalScrollBarDecorator(component) ;
        }
        component = new LegendDecorator(component) ;

        return new VerticalProduct(component.buildScene(formatBundle)) ;
    }
}
