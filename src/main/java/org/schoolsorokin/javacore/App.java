package org.schoolsorokin.javacore;

import org.schoolsorokin.javacore.basics.AnalysisOfRatings;
import org.schoolsorokin.javacore.basics.MovieSelection;

public class App 
{
    public static void main( String[] args )
    {
        AnalysisOfRatings analysis = new AnalysisOfRatings();
        analysis.analysis();

        MovieSelection selection = new MovieSelection();
        selection.movie();
    }
}
