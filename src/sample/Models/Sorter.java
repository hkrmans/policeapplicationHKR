package sample.Models;
import java.util.ArrayList;
import java.util.Collections;

public class Sorter {

    public void wantedCriminalsSorter(ArrayList<WantedCriminal> wantedCriminals){
        Collections.sort(wantedCriminals);
    }

    public void prisonerSorter(ArrayList<Prisoner> prisoners){
        Collections.sort(prisoners);
    }

    public void civilianSorter(ArrayList<Civilian> civilians){
        Collections.sort(civilians);
    }

    public void policeSorter(ArrayList<Police> officers){
        Collections.sort(officers);
    }

    public void convictionSorter(ArrayList<Conviction> convictions){
        Collections.sort(convictions);
    }

    public void crimeSorter(ArrayList<Crime> crimes){
        Collections.sort(crimes);
    }

    public void raportSorter(ArrayList<CrimeRapport> crimeRapports){
        Collections.sort(crimeRapports);
    }

    public void meetingSorter(ArrayList<Meeting> meetings){
        Collections.sort(meetings);
    }
}
