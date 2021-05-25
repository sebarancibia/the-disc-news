/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The class
 *
 * @author Sebastian Arancibia
 */
public abstract class BaseTest {

    /**
     * @param obj to transform.
     * @return the String view of the object
     */
    protected static String toString(final Object obj){
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.MULTI_LINE_STYLE);
    }
}
