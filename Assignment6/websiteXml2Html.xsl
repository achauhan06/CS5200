<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"  />
    <xsl:template match="website">
        <h1>Website</h1>
        <h2>Pages</h2>
        <ol>
            <xsl:for-each select="page">
                <li>
                    <xsl:value-of select="@name"/>, <xsl:value-of select="description"/>
                </li>
            </xsl:for-each>
        </ol>
        <h2>Widgets</h2>
        <table border = "1" cellpadding = "2">
            <thead>
                <tr>
                    <th>id</th>
                    <th>type</th>
                    <th>text</th>
                    <th>src</th>
                    <th>url</th>
                    <th>label</th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="/website/page/widget">
                <tr>
                    <td>
                        <xsl:value-of select="@id"></xsl:value-of>
                    </td>
                    <td>
                        <xsl:value-of select="@type"></xsl:value-of>
                    </td>
                    <td>
                        <xsl:value-of select="text"></xsl:value-of>
                    </td>
                    <td>
                        <xsl:value-of select="@src"></xsl:value-of>
                    </td>
                    <td>
                        <xsl:value-of select="@url"></xsl:value-of>
                    </td>
                    <td>
                        <xsl:value-of select="@label"></xsl:value-of>
                    </td>
                </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </xsl:template>

</xsl:stylesheet>