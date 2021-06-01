package com.runeterrahelper.meta.outputs;

import com.runeterrahelper.meta.processors.model.MetaReport;

/**
 * Define a writer the outputs a meta report in a given form. It can be implemented to ouput a meta report as a tweet,
 * as a CSV, as a HTML page or anything else.
 */
public interface MetaReportWriter {
    void writeReport(final MetaReport report);
}
