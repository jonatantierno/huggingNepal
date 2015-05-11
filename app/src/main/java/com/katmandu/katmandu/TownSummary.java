package com.katmandu.katmandu;

import java.util.Scanner;

/**
 * Created by jonatan on 5/05/15.
 */
public class TownSummary implements Comparable<TownSummary>{
    final String name;
    final TownStatus status;
    final String id;
    final boolean synchPending;
    final boolean isDraft;
    final String nameLowerCase;

    private TownSummary(String name, TownStatus status, String id, boolean synchPending, boolean draft) {
        this.name = name;
        this.nameLowerCase = name.toLowerCase();
        this.status = status;
        this.id = id;
        this.synchPending = synchPending;
        this.isDraft = draft;
    }

    public static TownSummary buildDraft(String name, TownStatus status, String draftId) {
        return new TownSummary(name,status, draftId,true,true);
    }

    public static TownSummary buildUpToDate(String name, TownStatus status, int serverId) {
        return new TownSummary(name,status,Integer.toString(serverId),false,false);
    }

    public static TownSummary buildModified(String name, TownStatus status, String id) {
        return new TownSummary(name,status,id,true,false);
    }

    public static TownSummary deserialize(String serialized) {
        Scanner scanner = new Scanner(serialized).useDelimiter("\n");

        return new TownSummary(
                scanner.next(),
                TownStatus.valueOf(scanner.next()),
                scanner.next(),
                scanner.nextBoolean(),
                scanner.nextBoolean());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(name);
        sb.append('\n');
        sb.append(status.toString());
        sb.append('\n');
        sb.append(id);
        sb.append('\n');
        sb.append(synchPending);
        sb.append('\n');
        sb.append(isDraft);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TownSummary)){
            return false;
        }
        TownSummary that = (TownSummary) o;

        return name.equals(that.name) &&
                status.equals(that.status) &&
                id.equals(that.id) &&
                synchPending == that.synchPending &&
                isDraft == that.isDraft;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + status.ordinal() >>> 2 + id.hashCode() >>> 3 + (isDraft ?57:0) + (synchPending?21:0);
    }

    @Override
    public int compareTo(TownSummary another) {
        return name.compareToIgnoreCase(another.name);
    }
}
