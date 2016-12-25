/**
 * Created by Qiang on 28/11/2016.
 */

const GlobalVariables = Object.freeze({
    port: 8090,
    host: 'localhost',
    context: '/'
});
export const baseUrl = 'http://' + GlobalVariables.host + ':' + GlobalVariables.port + GlobalVariables.context;
