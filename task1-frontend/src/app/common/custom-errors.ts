import {ErrorMessage, FormatErrorFunction} from "ng-bootstrap-form-validation";
 
export const CUSTOM_ERRORS: ErrorMessage[] = [
  {
    error: "required",
    format: requiredFormat
  }, 
  {
    error: "email",
    format: emailFormat
  },
  {
    error: 'requiredTrue',
    format: checkFormat
  }
  ,
  // {
  //   error: 'pattern',
  //   format: passwordFormat
  // }
];

//export type requiredFormat = (label:string, error:any)=>{return "Yes"; 
export function requiredFormat(label: any, error: any): string {
  return `${label} is required!`;
}
 
export function emailFormat(label: any, error: any): string {
  return `Not a valid email address.`;
}

export function checkFormat(label: any, error: any): string {
  return `Please accept terms and service.`;
}

// export function passwordFormat(label: string, error: any): string {
//   return `One UpperCase, LowerCase and Number required`;
// }